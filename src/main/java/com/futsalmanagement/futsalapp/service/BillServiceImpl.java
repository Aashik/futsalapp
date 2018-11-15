package com.futsalmanagement.futsalapp.service;


import com.futsalmanagement.futsalapp.dao.BillDao;
import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.entity.Discount;
import com.futsalmanagement.futsalapp.entity.Expense;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.model.BillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
