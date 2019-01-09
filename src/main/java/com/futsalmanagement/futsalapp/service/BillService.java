package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.model.SalesReport;
import java.math.BigDecimal;
import java.util.List;

public interface BillService {

    Bill generateBill(int game_id);
    BigDecimal calculateTotalDailySalesAmount(List<SalesReport> salesReportList);
    Bill getBillById(int bill_id);
}
