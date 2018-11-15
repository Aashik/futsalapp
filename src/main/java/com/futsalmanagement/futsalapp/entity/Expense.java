package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expense_id;
    private String particular;
    private double unit_price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public Expense(String particular, double unit_price, int quantity, Bill bill) {
        this.particular = particular;
        this.unit_price = unit_price;
        this.quantity = quantity;
        this.bill = bill;
    }

    public Expense() {
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public String getParticular() {
        return particular;
    }

    public void setParticular(String particular) {
        this.particular = particular;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}