package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.RequestObject;
import com.futsalmanagement.futsalapp.model.SalesReport;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ReportController {

    //task1
    //post api for getting dailysales
    //api should be able to get all the bills generated for a particular day

    @Autowired
    private BillService billService;

//    @RequestMapping(value = "api/getDailySalesReport" , method = RequestMethod.POST)
//    public ResponseEntity<GlobalResponse> getDailySalesReport(@RequestBody RequestObject request){
//        int futsal_id = request.getFutsal_id();
//        int ground_id = request.getGround_id();
//        List<SalesReport> saleslist = billService.getDailySales(request.getDate(),futsal_id,ground_id);
//        if (saleslist != null && saleslist.size() > 0) {
//            BigDecimal totalsales = billService.calculateTotalDailySalesAmount(saleslist);
//            Map<String, Object> salesResponse = new HashMap<>();
//            salesResponse.put("total sales", totalsales);
//            salesResponse.put("Sale list", saleslist);
//            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "sales list fetched", salesResponse);
//            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
//        }
//        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR , "null value retrieved. no any sales" , null);
//        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
 //   }




}
