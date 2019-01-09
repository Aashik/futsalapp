package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;
    private String customer_name;
    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;
    private BigDecimal play_amount;
    private BigDecimal addition_expense_amount;
    private int discount_margin;
    private BigDecimal discountable_amount;
    private BigDecimal total_Amount;
    @Column(name = "billing_date", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date billing_date;

    public Bill(String customer_name, Game game, BigDecimal play_amount, BigDecimal addition_expense_amount, int discount_margin, BigDecimal discountable_amount, Date billing_date) {
        this.customer_name = customer_name;
        this.game = game;
        this.play_amount = play_amount;
        this.addition_expense_amount = addition_expense_amount;
        this.discount_margin = discount_margin;
        this.discountable_amount = discountable_amount;
        this.billing_date = billing_date;
    }

    public Bill() {
    }

    public Bill(int bill_id,String customer_name, BigDecimal play_amount, BigDecimal addition_expense_amount, int discount_margin, BigDecimal discountable_amount, BigDecimal total_Amount, Date billing_date) {
        this.bill_id = bill_id;
        this.customer_name = customer_name;
        this.play_amount = play_amount;
        this.addition_expense_amount = addition_expense_amount;
        this.discount_margin = discount_margin;
        this.discountable_amount = discountable_amount;
        this.total_Amount = total_Amount;
        this.billing_date = billing_date;
    }

    public Bill inAnotherFormat(){
        return new Bill(this.bill_id,this.customer_name,this.play_amount,this.addition_expense_amount,this.discount_margin,this.discountable_amount,this.total_Amount,this.billing_date);
    }



    public BigDecimal getTotal_Amount() {
        return total_Amount;
    }

    public void setTotal_Amount(BigDecimal total_Amount) {
        this.total_Amount = total_Amount;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public int getDiscount_margin() {
        return discount_margin;
    }

    public void setDiscount_margin(int discount_margin) {
        this.discount_margin = discount_margin;
    }

    public BigDecimal getDiscountable_amount() {
        return discountable_amount;
    }

    public void setDiscountable_amount(BigDecimal discountable_amount) {
        this.discountable_amount = discountable_amount;
    }

    public Date getBilling_date() {
        return billing_date;
    }

    public void setBilling_date(Date billing_date) {
        this.billing_date = billing_date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bill_id=" + bill_id +
                ", customer_name='" + customer_name + '\'' +
                ", game=" + game +
                ", play_amount=" + play_amount +
                ", addition_expense_amount=" + addition_expense_amount +
                ", discount_margin=" + discount_margin +
                ", discountable_amount=" + discountable_amount +
                ", billing_date=" + billing_date +
                '}';
    }
}
