package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Discount;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.DiscountService;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.futsalmanagement.futsalapp.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class DiscountController {

    @Autowired
    private DiscountService discountService;
    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;


    @RequestMapping(value = "api/addDiscount" , method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addDiscount(@RequestBody Discount discount,
                                                      @RequestParam("futsal_id") int futsal_id,
                                                      @RequestParam("ground_id") int ground_id){

        int size = discount.getDiscount_weekdays().length;
        int discountMargin = discount.getDiscount_margin();
        if (size > 0 && size <= 7 && discountMargin > 5 && discountMargin < 100) {
            if (futsalService.checkFutsalAvailability(futsal_id) && groundService.checkGroundAvailability(futsal_id, ground_id)) {
                discount.setFutsal(futsalService.getFutsalById(futsal_id));
                discount.setGround(groundService.getGroundById(futsal_id, ground_id));
                Discount insertedDiscount = discountService.addDiscount(discount);
                if (insertedDiscount != null) {
                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "discount created successfully", insertedDiscount);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Could not process request", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal or ground", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "The request data is not valid", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "api/getallAvailableDiscount" , method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getAllAvailableDiscount(@RequestParam("futsal_id") int futsal_id){

        List<Discount> discountList = discountService.getAllDiscountForAFutsal(futsal_id);
        List<Discount> anotherformatdiscountlist = discountList.stream().map(discount -> discount.toAnotherFormat()).collect(Collectors.toList());
        if (discountList != null){
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "all discounts for specified futsal", anotherformatdiscountlist);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "no discount available for the futsal", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
