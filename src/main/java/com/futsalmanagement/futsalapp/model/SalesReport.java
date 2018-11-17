package com.futsalmanagement.futsalapp.model;

import java.math.BigDecimal;

public class SalesReport {

    private String ground_name;
    private String person_name;
    private String date_time;
    private double play_duration;
    private BigDecimal play_cost;
    private BigDecimal additional_cost;


    public SalesReport(String ground_name, String person_name, String date_time, double play_duration, BigDecimal play_cost) {
        this.ground_name = ground_name;
        this.person_name = person_name;
        this.date_time = date_time;
        this.play_duration = play_duration;
        this.play_cost = play_cost;
    }

    public SalesReport() {
    }

    public BigDecimal getPlay_cost() {
        return play_cost;
    }

    public void setPlay_cost(BigDecimal play_cost) {
        this.play_cost = play_cost;
    }

    public BigDecimal getAdditional_cost() {
        return additional_cost;
    }

    public void setAdditional_cost(BigDecimal additional_cost) {
        this.additional_cost = additional_cost;
    }

    public String getGround_name() {
        return ground_name;
    }

    public void setGround_name(String ground_name) {
        this.ground_name = ground_name;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public double getPlay_duration() {
        return play_duration;
    }

    public void setPlay_duration(double play_duration) {
        this.play_duration = play_duration;
    }

    @Override
    public String toString() {
        return "SalesReport{" +
                "ground_name='" + ground_name + '\'' +
                ", person_name='" + person_name + '\'' +
                ", date_time='" + date_time + '\'' +
                ", play_duration=" + play_duration +
                '}';
    }
}
