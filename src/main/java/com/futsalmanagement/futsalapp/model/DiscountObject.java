package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.entity.DiscountDetail;

import java.util.List;

public class DiscountObject {

    private int discount_master_id;
    private String discount_name;
    private int discount_type;
    private String remarks;
    private int futsal_id;
    private int ground_id;
    private List<DiscountDetail> discountDetails;

    public DiscountObject(String discount_name, int discount_type, String remarks, int futsal_id, int ground_id, List<DiscountDetail> discountDetails) {
        this.discount_name = discount_name;
        this.discount_type = discount_type;
        this.remarks = remarks;
        this.futsal_id = futsal_id;
        this.ground_id = ground_id;
        this.discountDetails = discountDetails;
    }

    public DiscountObject() {
    }

    public int getDiscount_master_id() {
        return discount_master_id;
    }

    public void setDiscount_master_id(int discount_master_id) {
        this.discount_master_id = discount_master_id;
    }


    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getFutsal_id() {
        return futsal_id;
    }

    public void setFutsal_id(int futsal_id) {
        this.futsal_id = futsal_id;
    }

    public int getGround_id() {
        return ground_id;
    }

    public void setGround_id(int ground_id) {
        this.ground_id = ground_id;
    }

    public List<DiscountDetail> getDiscountDetails() {
        return discountDetails;
    }

    public void setDiscountDetails(List<DiscountDetail> discountDetails) {
        this.discountDetails = discountDetails;
    }
}
