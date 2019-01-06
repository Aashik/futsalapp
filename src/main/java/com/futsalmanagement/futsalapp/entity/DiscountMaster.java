package com.futsalmanagement.futsalapp.entity;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;

@Entity
public class DiscountMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discount_master_id;
    private String discount_name;
    private int discount_type;
    private String remarks;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;
    @Column(name = "insert_time", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insert_time;

    public DiscountMaster(String discount_name, int discount_type, String remarks, Futsal futsal, Ground ground) {
        this.discount_name = discount_name;
        this.discount_type = discount_type;
        this.remarks = remarks;
        this.futsal = futsal;
        this.ground = ground;
    }

    public DiscountMaster(int discount_master_id, String discount_name, int discount_type, String remarks, Date insert_time) {
        this.discount_master_id = discount_master_id;
        this.discount_name = discount_name;
        this.discount_type = discount_type;
        this.remarks = remarks;
        this.insert_time = insert_time;
    }

    public DiscountMaster inAnotherFormat() {
       return new DiscountMaster(this.discount_master_id, this.discount_name,this.discount_type,this.remarks,this.insert_time);
    }

    public DiscountMaster() {
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }

    public int getDiscount_master_id() {
        return discount_master_id;
    }

    public void setDiscount_master_id(int discount_master_id) {
        this.discount_master_id = discount_master_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return "DiscountMaster{" +
                "discount_master_id=" + discount_master_id +
                ", discount_name='" + discount_name + '\'' +
                ", discount_type=" + discount_type +
                ", remarks='" + remarks + '\'' +
                ", futsal=" + futsal +
                ", ground=" + ground +
                '}';
    }
}