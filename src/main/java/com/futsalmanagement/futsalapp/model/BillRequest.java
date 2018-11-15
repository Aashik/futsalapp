package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.entity.Expense;

import java.util.List;

public class BillRequest {

    private String play_start_time;
    private double play_duration;
    private String book_person_name;
    private int futsal_id;
    private int ground_id;
    private List<Expense> expenseList;

    public BillRequest(String play_start_time, double play_duration, String book_person_name, int futsal_id, int ground_id, List<Expense> expenseList) {
        this.play_start_time = play_start_time;
        this.play_duration = play_duration;
        this.book_person_name = book_person_name;
        this.futsal_id = futsal_id;
        this.ground_id = ground_id;
        this.expenseList = expenseList;
    }

    public BillRequest() {
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

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }
}
