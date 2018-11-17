package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Discount;

import java.util.List;

public interface DiscountService {

     Discount addDiscount(Discount d);
     List<Discount> getAllDiscount();
     boolean isDiscountAvailable(int futsal_id, int ground_id, String play_date);
     Discount getDiscountById(int futsal_id, int ground_id);
     List<Discount> getAllDiscountForAFutsal(int futsal_id);
}
