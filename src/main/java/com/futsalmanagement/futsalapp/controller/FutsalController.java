package com.futsalmanagement.futsalapp.controller;


import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.futsalmanagement.futsalapp.service.GroundService;
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

    @Autowired
    private GroundService groundService;

    @RequestMapping(value = "api/addFutsal", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addFutsal(@RequestBody Futsal futsal) {

        if (futsalService.checkFutsalAvailability(futsal.getFutsal_id())) {
            Futsal foundfutsal = futsalService.getFutsalById(futsal.getFutsal_id());
            futsal.setAddress(foundfutsal.getAddress());
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

    @RequestMapping(value = "api/addGround", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addGround(@RequestBody Ground g, @RequestParam("futsal_id") int futsal_id) {

        Futsal foundFutsal = futsalService.getFutsalById(futsal_id);

        System.out.println("found footsall -->> "+ foundFutsal);

        if (foundFutsal != null) {
          g.setFutsal(foundFutsal);
            System.out.println("request ground -->> "+ g);
            Ground insertedGround = groundService.insert(g);
            if (insertedGround != null) {
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "Ground added successfully", insertedGround);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Cannot perform Action.Try Again", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal code .Try Again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

}









