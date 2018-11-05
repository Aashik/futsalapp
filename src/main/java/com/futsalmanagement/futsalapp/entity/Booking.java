package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.futsalmanagement.futsalapp.model.Status;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int booking_id;
    @Size(max = 50)
    private String booking_code;
    @Size(max = 50)
    private String full_name;
    @Size(max = 20)
    private String contact_num;
    @Size(max = 10)
    private String booking_date;
    @Size(max = 10)
    private String booking_time;
    //in hours like 1 hrs of 2.5 hrs
    private double booking_duration;
    private String booking_status;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal bookFutsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground bookGround;


    public Booking() {
    }

    public Booking(int booking_id, @Size(max = 50) String booking_code, @Size(max = 50) String full_name, @Size(max = 20) String contact_num, @Size(max = 10) String booking_date, @Size(max = 10) String booking_time, double booking_duration, String booking_status, Ground book_ground) {
        this.booking_id = booking_id;
        this.booking_code = booking_code;
        this.full_name = full_name;
        this.contact_num = contact_num;
        this.booking_date = booking_date;
        this.booking_time = booking_time;
        this.booking_duration = booking_duration;
        this.booking_status = booking_status;
        this.bookGround = book_ground;
    }

    public Booking genericFormat(){
        return new Booking(this.booking_id, this.booking_code, this.full_name, this.contact_num,
                this.booking_date,this.booking_time, this.booking_duration, this.booking_status,this.bookGround);
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_code() {
        return booking_code;
    }

    public void setBooking_code(String booking_code) {
        this.booking_code = booking_code;
    }

    public String getFullname() {
        return full_name;
    }

    public void setFullname(String fullname) {
        this.full_name = fullname;
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

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public Futsal getBookFutsal() {
        return bookFutsal;
    }

    public void setBookFutsal(Futsal bookFutsal) {
        this.bookFutsal = bookFutsal;
    }

    public Ground getBookGround() {
        return bookGround;
    }

    public void setBookGround(Ground bookGround) {
        this.bookGround = bookGround;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_id=" + booking_id +
                ", booking_code='" + booking_code + '\'' +
                ", full_name='" + full_name + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", booking_date='" + booking_date + '\'' +
                ", booking_time='" + booking_time + '\'' +
                ", booking_duration=" + booking_duration +
                ", booking_status='" + booking_status + '\'' +
                '}';
    }


}

