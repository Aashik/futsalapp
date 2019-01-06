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
    private String weekday ;
    private String date_from;
    private String date_to;
    private String time_from;
    private String time_to;
    private int margin;
    private String status;

    public DiscountDetail(DiscountMaster discountMaster, String weekday, String date_from, String date_to, String time_from, String time_to, int margin, String status) {
        this.discountMaster = discountMaster;
        this.weekday = weekday;
        this.date_from = date_from;
        this.date_to = date_to;
        this.time_from = time_from;
        this.time_to = time_to;
        this.margin = margin;
        this.status = status;
    }

    public DiscountDetail(int discount_detail_id,String weekday, String date_from, String date_to, String time_from, String time_to, int margin, String status) {
        this.discount_detail_id = discount_detail_id;
        this.weekday = weekday;
        this.date_from = date_from;
        this.date_to = date_to;
        this.time_from = time_from;
        this.time_to = time_to;
        this.margin = margin;
        this.status = status;
    }

    public DiscountDetail inAnotherFormat(){
        return new DiscountDetail(this.discount_detail_id,this.weekday,this.date_from,this.date_to,this.time_from,this.time_to,this.margin,this.status);
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DiscountDetail{" +
                "discount_detail_id=" + discount_detail_id +
                ", discountMaster=" + discountMaster +
                ", weekday='" + weekday + '\'' +
                ", date_from='" + date_from + '\'' +
                ", date_to='" + date_to + '\'' +
                ", time_from='" + time_from + '\'' +
                ", time_to='" + time_to + '\'' +
                ", margin=" + margin +
                ", status=" + status +
                '}';
    }
}