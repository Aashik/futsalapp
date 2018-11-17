package com.futsalmanagement.futsalapp.service;


import com.futsalmanagement.futsalapp.dao.BillDao;
import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.entity.Discount;
import com.futsalmanagement.futsalapp.entity.Expense;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.model.BillRequest;
import com.futsalmanagement.futsalapp.model.SalesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private GroundService groundService;

    @Override
    public Bill insert(Bill bill) {
       Bill insertedbill = billDao.save(bill);
       return insertedbill;
    }


    @Override
    public BigDecimal calculateTotalPrice(BillRequest billRequest) {
        double unitPrice = 0.0;
        int futsal_id = billRequest.getFutsal_id();
        int ground_id = billRequest.getGround_id();
        unitPrice = groundService.getGroundById(futsal_id,ground_id).getUnit_hour_price().doubleValue();

        if (discountService.isDiscountAvailable(futsal_id,ground_id, billRequest.getPlay_start_time())){
            Discount discount = discountService.getDiscountById(futsal_id, ground_id);
            //in percentage
            int discountmargin = discount.getDiscount_margin();

            float discountmarginvalue = (float)discountmargin/100;
            System.out.println("discoutnmargin value " + discountmarginvalue);
            double todiscountprice = discountmarginvalue * unitPrice;
            System.out.println("to duscount price  " + todiscountprice);
            unitPrice = unitPrice - todiscountprice;
            System.out.println("the unit price after discount  " + unitPrice );
        }

        double playcost = billRequest.getPlay_duration() * unitPrice ;
        double extraexpense = 0.0 ;

        for (Expense expense : billRequest.getExpenseList()){
            extraexpense = extraexpense +  (expense.getUnit_price() * expense.getQuantity());
        }

        double totalcost = playcost + extraexpense;
        return new BigDecimal(totalcost);

    }

    @Override
    public List<SalesReport> getDailySales(String date, int futsal_id, int ground_id) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        List<SalesReport> saleslist = new ArrayList<>();

        try {
            Date requesteddate = formater.parse(date);
            System.out.println("requested date " + requesteddate);
            for (Bill bill : billDao.findAll()){

            String eachdate = formater.format(bill.getBilling_date());

            boolean checkfutsal = bill.getFutsal().getFutsal_id() == futsal_id;
            boolean checkground = bill.getGround().getGround_id() == ground_id;

            if (date.equals(eachdate) && checkfutsal && checkground ) {
                SalesReport salesReport = new SalesReport();
                salesReport.setPerson_name(bill.getBook_person_name());
                salesReport.setGround_name(bill.getGround().getGround_name());
                salesReport.setDate_time(bill.getBilling_date().toString());
                salesReport.setPlay_duration(bill.getPlay_duration());
                salesReport.setTotal_cost(bill.getTotal_amount());
                saleslist.add(salesReport);
            }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return saleslist;
    }

    @Override
    public BigDecimal calculateTotalDailySalesAmount(List<SalesReport> salesReportList) {
        double totalAmount = 0.0;
        for (SalesReport sales : salesReportList){
            totalAmount = totalAmount + sales.getTotal_cost().doubleValue();
        }
        return new BigDecimal(totalAmount);
    }
}
