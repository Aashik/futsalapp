package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Futsal;

public interface FutsalService {

    Futsal insert(Futsal f);
    boolean checkFutsalDuplication(String futsal_code);
    Futsal getFutsalByCode(String futsal_code);
}
