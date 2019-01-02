package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;

@Entity
public class DiscountDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discount_detail_id;
    @ManyToOne
    @JoinColumn(name = "discount_master_id")
    private Discount discount;
    private String weekday ;
    private String date_from;
    private String date_to;
    private String time_from;
    private String time_to;
    private int margin;
    private boolean status;

    public DiscountDetail() {
    }

    public int getDiscount_detail_id() {
        return discount_detail_id;
    }

    public void setDiscount_detail_id(int discount_detail_id) {
        this.discount_detail_id = discount_detail_id;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
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

    public String getTime_froml() {
        return time_from;
    }

    public void setTime_froml(String time_froml) {
        this.time_from = time_froml;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
