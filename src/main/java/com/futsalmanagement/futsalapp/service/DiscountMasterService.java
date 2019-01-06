package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.model.DiscountObject;

import java.util.List;
import java.util.Map;


public interface DiscountMasterService {

    int saveDiscount(DiscountObject discountObject);
    List<Map<String, Object>> getAllDiscountForAFutsal(int futsal_id);


















































}
