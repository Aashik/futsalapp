package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.model.BillRequest;
import com.futsalmanagement.futsalapp.model.SalesReport;

import java.math.BigDecimal;
import java.util.List;

public interface BillService {

    Bill insert(Bill bill);
    BigDecimal calculateTotalPrice(BillRequest billRequest);
    //date should be specifically in format "yyyy-MM-dd"
    List<SalesReport> getDailySales(String date, int futsal_id, int ground_id);
    BigDecimal calculateTotalDailySalesAmount(List<SalesReport> salesReportList);
}
