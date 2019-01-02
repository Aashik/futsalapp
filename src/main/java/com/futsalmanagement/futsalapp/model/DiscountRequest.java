package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.dao.GroundDao;
import com.futsalmanagement.futsalapp.entity.DiscountDetail;
import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.entity.Ground;


import javax.persistence.JoinColumn;
import java.util.List;

public class DiscountRequest {

    private String discount_name;
    private int discount_type;
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
    @JoinColumn(name = "ground_id")
    private Ground ground;
    private String description;

    public DiscountRequest() {
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

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public Ground getGround() {
        return ground;
    }

    public void setGround(Ground ground) {
        this.ground = ground;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
