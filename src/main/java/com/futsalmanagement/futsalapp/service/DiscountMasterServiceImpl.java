package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.DiscountDetailDao;
import com.futsalmanagement.futsalapp.dao.DiscountMasterDao;
import com.futsalmanagement.futsalapp.entity.DiscountDetail;
import com.futsalmanagement.futsalapp.entity.DiscountMaster;
import com.futsalmanagement.futsalapp.model.DiscountObject;
import com.futsalmanagement.futsalapp.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiscountMasterServiceImpl implements DiscountMasterService {

    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;
    @Autowired
    private DiscountMasterDao discountMasterDao;
    @Autowired
    private DiscountDetailDao discountDetailDao;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private GameService gameService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveDiscount(DiscountObject discountObject){
        if (validateDiscountType(discountObject)) {
            boolean checkparam = (discountObject.getDiscount_type() == 1) ? validateWeekDays(discountObject.getDiscountDetails()) :
                    validateDate(discountObject.getDiscountDetails());
            if (checkparam){
                DiscountMaster discountMaster = new DiscountMaster();
                discountMaster.setDiscount_name(discountObject.getDiscount_name());
                discountMaster.setDiscount_type(discountObject.getDiscount_type());
                discountMaster.setRemarks(discountObject.getRemarks());
                discountMaster.setFutsal(futsalService.getFutsalById(discountObject.getFutsal_id()));
                discountMaster.setGround(groundService.getGroundById(discountObject.getFutsal_id(), discountObject.getGround_id()));
                DiscountMaster savedDiscountMaster = discountMasterDao.save(discountMaster);
                int count = 0;
                if (savedDiscountMaster != null) {
                    for (DiscountDetail discountDetail : discountObject.getDiscountDetails()){
                        discountDetail.setDiscountMaster(savedDiscountMaster);
                        discountDetail.setStatus(Status.ACTIVE.toString());
                        DiscountDetail savedDiscountDetail = discountDetailDao.save(discountDetail);
                        if (savedDiscountDetail != null) count = count + 1;
                    }
                    if (discountObject.getDiscountDetails().size() == count) {
                        return 1;
                    }
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public List<Map<String, Object>> getAllDiscountForAFutsal(int futsal_id) {


        List<Map<String,Object>> listofDiscounts = new ArrayList<>();
        List<DiscountMaster> discountMasterList = discountMasterDao.findAll().stream()
                .filter(discountMaster -> discountMaster.getFutsal().getFutsal_id() == futsal_id)
                .collect(Collectors.toList());
        for (DiscountMaster discountMaster : discountMasterList){
            Map<String,Object> discountMap = new HashMap<>();
            int discountMasterId = discountMaster.getDiscount_master_id();
            List<DiscountDetail> discountDetailList = discountDetailDao.findAll().stream()
                    .filter(discountDetail -> discountDetail.getDiscountMaster().getDiscount_master_id() == discountMasterId)
                    .collect(Collectors.toList());
            discountMap.put("discount_details",discountDetailList.stream()
                    .map(discountDetail -> discountDetail.inAnotherFormat()).collect(Collectors.toList()));
            discountMap.put("discount_master", discountMaster.inAnotherFormat());
            listofDiscounts.add(discountMap);
        }
        return listofDiscounts;
    }

    private boolean validateDate(List<DiscountDetail> discountDetails) {
        for (DiscountDetail discountDetail : discountDetails) {
            boolean checkdatefrom = !bookingService.ifPastDate(discountDetail.getDate_from()) || gameService.checkifTodayDate(discountDetail.getDate_from());
            boolean checkdateto = !bookingService.ifPastDate(discountDetail.getDate_to()) || gameService.checkifTodayDate(discountDetail.getDate_to());
            if (checkdatefrom == false || checkdateto == false) return false;
        }
        return true;
    }


    private boolean validateWeekDays(List<DiscountDetail> discountDetails) {
        List<String> weekdays = Arrays.asList("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT");
        for (DiscountDetail discountDetail : discountDetails) {
            boolean checkeach = weekdays.contains(discountDetail.getWeekday());
            if (checkeach == false) return false;
        }
        return true;
    }

    private boolean validateDiscountType(DiscountObject discountObject) {
        int disType = discountObject.getDiscount_type();
        if (disType == 1) {
            DiscountDetail founddiscountDetail = discountObject.getDiscountDetails().stream()
                    .filter(discountDetail -> discountDetail.getWeekday()== null
                            && discountDetail.getDate_from() != null && discountDetail.getDate_to() != null).findAny().orElse(null);
            System.out.println("found discount for 1" + founddiscountDetail);
            return (founddiscountDetail != null) ? false : true;

        } else if (disType == 2) {
            DiscountDetail founddiscountDetail2 = discountObject.getDiscountDetails().stream()
                    .filter(discountDetail -> discountDetail.getDate_from() == null && discountDetail.getDate_to() == null
                            && discountDetail.getWeekday() != null ).findAny().orElse(null);
            System.out.println("found discount for 2 " + founddiscountDetail2);
            return (founddiscountDetail2 != null) ? false : true;
        }
        return false;
    }
}
