package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.entity.DiscountDetail;

import java.util.List;

public class DiscountObject {

    private int discount_master_id;
    private String discount_name;
    private String date_from;
    private String date_to;
    private String status;
    private String remarks;
    private int futsal_id;
    private int ground_id;
    private List<DiscountDetail> discountDetails;

    public DiscountObject(String discount_name, String remarks, int futsal_id, int ground_id, List<DiscountDetail> discountDetails) {
        this.discount_name = discount_name;
        this.remarks = remarks;
        this.futsal_id = futsal_id;
        this.ground_id = ground_id;
        this.discountDetails = discountDetails;
    }

    public DiscountObject() {
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
