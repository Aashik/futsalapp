package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;
    private String play_start_time;
    //in hours
    private double play_duration;
    private String book_person_name;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;
    private BigDecimal play_amount;
    private BigDecimal addition_expense_amount;
    @Column(name = "billing_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date billing_date;

    public Bill(String play_start_time, double play_duration, String book_person_name, Futsal futsal, Ground ground, BigDecimal play_amount, Date billing_date) {
        this.play_start_time = play_start_time;
        this.play_duration = play_duration;
        this.book_person_name = book_person_name;
        this.futsal = futsal;
        this.ground = ground;
        this.play_amount = play_amount;
        this.billing_date = billing_date;
    }
    public Bill(int bill_id, String play_start_time, double play_duration, String book_person_name,BigDecimal play_amount, Date billing_date,Futsal futsal){
        this.bill_id = bill_id;
        this.play_start_time = play_start_time;
        this.play_duration = play_duration;
        this.book_person_name = book_person_name;
        this.play_amount = play_amount;
        this.billing_date = billing_date;
        this.futsal = futsal;
    }

    public Bill() {
    }

    public Bill inSimpleFormat(){
       return new Bill( this.bill_id, this.play_start_time, this.play_duration, this.book_person_name, this.play_amount, this.billing_date,
        this.futsal);

    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getPlay_start_time() {
        return play_start_time;
    }

    public void setPlay_start_time(String play_start_time) {
        this.play_start_time = play_start_time;
    }

    public double getPlay_duration() {
        return play_duration;
    }

    public void setPlay_duration(double play_duration) {
        this.play_duration = play_duration;
    }

    public String getBook_person_name() {
        return book_person_name;
    }

    public void setBook_person_name(String book_person_name) {
        this.book_person_name = book_person_name;
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

    public BigDecimal getPlay_amount() {
        return play_amount;
    }

    public void setPlay_amount(BigDecimal play_amount) {
        this.play_amount = play_amount;
    }

    public BigDecimal getAddition_expense_amount() {
        return addition_expense_amount;
    }

    public void setAddition_expense_amount(BigDecimal addition_expense_amount) {
        this.addition_expense_amount = addition_expense_amount;
    }

    public Date getBilling_date() {
        return billing_date;
    }

    public void setBilling_date(Date billing_date) {
        this.billing_date = billing_date;
    }
}
