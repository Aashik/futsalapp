package com.futsalmanagement.futsalapp.model;


public class BookingRequest {
    private String full_name;
    private String contact_num;
    //optional
    private String address;
    //optional
    private String email;
    private String booking_date;
    private String booking_time;
    private double booking_duration;
    private int futsal_id;
    private int ground_id;

    public BookingRequest(String full_name, String contact_num, String booking_date, String booking_time, double booking_duration, int futsal_id, int ground_id) {
        this.full_name = full_name;
        this.contact_num = contact_num;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
        this.booking_duration = booking_duration;
        this.futsal_id = futsal_id;
        this.ground_id = ground_id;
    }

    public BookingRequest() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public double getBooking_duration() {
        return booking_duration;
    }

    public void setBooking_duration(double booking_duration) {
        this.booking_duration = booking_duration;
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

    @Override
    public String toString() {
        return "BookingRequest{" +
                "full_name='" + full_name + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", booking_date='" + booking_date + '\'' +
                ", booking_time='" + booking_time + '\'' +
                ", booking_duration=" + booking_duration +
                ", futsal_id=" + futsal_id +
                ", ground_id=" + ground_id +
                '}';
    }
}
