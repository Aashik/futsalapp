package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.model.BillRequest;

import java.math.BigDecimal;

public interface BillService {

    Bill insert(Bill bill);
    BigDecimal calculateTotalPrice(BillRequest billRequest);
}
