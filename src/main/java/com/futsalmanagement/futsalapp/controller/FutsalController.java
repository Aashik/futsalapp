package com.futsalmanagement.futsalapp.controller;


import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FutsalController {

    @Autowired
    private FutsalService futsalService;

    @RequestMapping(value = "api/addFutsal", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addFutsal(@RequestBody Futsal futsal) {

        if (!futsalService.checkFutsalDuplication(futsal.getFutsal_code())) {

            Futsal insertedFutsal = futsalService.insert(futsal);
            if (insertedFutsal != null) {
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "futsal added successfully", insertedFutsal);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Cannot perform Action. Try again", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }

        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Duplicate futsal. Try again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }
}
