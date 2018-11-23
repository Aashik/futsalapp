package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Address;
import com.futsalmanagement.futsalapp.entity.Futsal;

import java.util.List;

public interface FutsalService {

    Futsal insert(Futsal f);
    boolean checkFutsalAvailability(int futsal_id);
//    Futsal getFutsalByCode(String futsal_code);
    Futsal getFutsalById(int futsal_id);
    List<String> getAllFutsalName();

}
