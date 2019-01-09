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
    @Transactional
    public Map<String, Object> saveDiscount(DiscountObject discountObject) {
        Map<String,Object> resultMap = new HashMap<>();
        //validate date from and date to
        if (validateDate(discountObject.getDate_from(), discountObject.getDate_to())){
            DiscountMaster discountMaster = new DiscountMaster();
            discountMaster.setDiscount_name(discountObject.getDiscount_name());
            discountMaster.setDate_from(discountObject.getDate_from());
            discountMaster.setDate_to(discountObject.getDate_to());
            discountMaster.setRemarks(discountObject.getRemarks());
            discountMaster.setFutsal(futsalService.getFutsalById(discountObject.getFutsal_id()));
            discountMaster.setGround(groundService.getGroundById(discountObject.getFutsal_id(),discountObject.getGround_id()));
            discountMaster.setStatus(Status.ACTIVE.toString());
            DiscountMaster saveDiscountMaster = discountMasterDao.save(discountMaster);
            int count = 0;
            if (saveDiscountMaster != null){
                for (DiscountDetail discountDetail : discountObject.getDiscountDetails()){
                    discountDetail.setDiscountMaster(saveDiscountMaster);
                    DiscountDetail savediscountDetail1 = discountDetailDao.save(discountDetail);
                    if (savediscountDetail1 != null) count = count + 1;
                }
                if (discountObject.getDiscountDetails().size() == count){
                    resultMap.put("response_code", 1);
                    resultMap.put("message", "discount inserted successfully");
                    return resultMap;
                }
            }
            resultMap.put("message", "Sorry Invalid opertation");
        }
        resultMap.put("message", "sorry Invalid date type. Please check again");
        resultMap.put("response_code" , 0);
        return resultMap;
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

    private boolean validateDate(String date_from, String date_to) {
        boolean checkdatefrom = !bookingService.ifPastDate(date_from) || gameService.checkifTodayDate(date_from);
        boolean checkdateto = !bookingService.ifPastDate(date_to) || gameService.checkifTodayDate(date_to);
        return  checkdatefrom && checkdateto;
    }

    @Override
    public DiscountMaster updateDiscountStatus(char status, int discount_master_id) {
        if (status == 'Y' || status == 'y' || status == 'N' || status == 'n'){
            DiscountMaster discountMaster = discountMasterDao.getOne(discount_master_id);
            discountMaster.setStatus(status == 'N' || status == 'n' ? Status.INACTIVE.toString(): Status.ACTIVE.toString());
            DiscountMaster discountMaster1 = discountMasterDao.save(discountMaster);
            return discountMaster1;
        }
        return null;

    }
}
