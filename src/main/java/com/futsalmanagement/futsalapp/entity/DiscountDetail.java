package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;

@Entity
public class DiscountDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discount_detail_id;
    @ManyToOne
    @JoinColumn(name = "discount_master_id")
    private DiscountMaster discountMaster;
    private String time_from;
    private String time_to;
    private int margin;

    public DiscountDetail(int discount_detail_id, String time_from, String time_to, int margin) {
        this.discount_detail_id= discount_detail_id;
        this.time_from = time_from;
        this.time_to = time_to;
        this.margin = margin;
    }

    public DiscountDetail inAnotherFormat(){
        return new DiscountDetail(this.discount_detail_id,this.time_from,this.time_to,this.margin);
    }

    public DiscountDetail() {
    }

    public int getDiscount_detail_id() {
        return discount_detail_id;
    }

    public void setDiscount_detail_id(int discount_detail_id) {
        this.discount_detail_id = discount_detail_id;
    }

    public DiscountMaster getDiscountMaster() {
        return discountMaster;
    }

    public void setDiscountMaster(DiscountMaster discountMaster) {
        this.discountMaster = discountMaster;
    }


    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_to() {
        return time_to;
    }

    public void setTime_to(String time_to) {
        this.time_to = time_to;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    @Override
    public String toString() {
        return "DiscountDetail{" +
                "discount_detail_id=" + discount_detail_id +
                ", discountMaster=" + discountMaster +
                ", time_from='" + time_from + '\'' +
                ", time_to='" + time_to + '\'' +
                ", margin=" + margin +
                '}';
    }
}