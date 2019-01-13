package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.DiscountMaster;
import com.futsalmanagement.futsalapp.model.DiscountObject;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.DiscountMasterService;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.futsalmanagement.futsalapp.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class DiscountController {

    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;
    @Autowired
    private DiscountMasterService discountMasterService;


    @RequestMapping(value = "api/addDiscount", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addDiscount(@RequestBody DiscountObject discountObject){
        if (futsalService.checkFutsalAvailability(discountObject.getFutsal_id())
                && groundService.checkGroundAvailability(discountObject.getFutsal_id(), discountObject.getGround_id())) {
            Map  resultMap = discountMasterService.saveDiscount(discountObject);
            int responseCode = Integer.parseInt(resultMap.get("response_code").toString());
            String message = (String)resultMap.get("message");
            if (responseCode == 1) {
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, message, null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, message, null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "invalid futsal or ground id ", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/getallAvailableDiscount", method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getAllAvailableDiscount(@RequestParam("futsal_id") int futsal_id){

        List<Map<String, Object>> discoultlist  = discountMasterService.getAllDiscountForAFutsal(futsal_id);
        if (discoultlist.size() > 0){
            GlobalResponse response = new GlobalResponse(Status.SUCCESS,"discount retrieved" , discoultlist);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.SUCCESS,"discount retrieved" , discoultlist);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "api/updateDiscountStatus" , method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> updateDiscountStatus(@RequestBody Map<String, Object> requestParam){
        int discount_master_id = Integer.parseInt(requestParam.get("discount_master_id").toString());
        char request_status = requestParam.get("discount_status").toString().charAt(0);

        DiscountMaster updatediscountMaster = discountMasterService.updateDiscountStatus(request_status,discount_master_id);
        if (updatediscountMaster != null){
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "status updated successfully", updatediscountMaster.inAnotherFormat());
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "sorry invalid request. cannot update", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }




}
