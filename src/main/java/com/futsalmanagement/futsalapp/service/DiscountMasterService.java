package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.DiscountMaster;
import com.futsalmanagement.futsalapp.model.DiscountObject;

import java.util.List;
import java.util.Map;


public interface DiscountMasterService {

    Map<String,Object> saveDiscount(DiscountObject discountObject);
    List<Map<String, Object>> getAllDiscountForAFutsal(int futsal_id);
    DiscountMaster updateDiscountStatus(char status, int discount_master_id);


















































}
