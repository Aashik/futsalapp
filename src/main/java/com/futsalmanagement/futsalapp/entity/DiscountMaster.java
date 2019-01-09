package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DiscountMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int discount_master_id;
    private String discount_name;
    private String date_from;
    private String date_to;
    private String remarks;
    private String status;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;
    @Column(name = "insert_time", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date insert_time;

    public DiscountMaster(String discount_name, String remarks, Futsal futsal, Ground ground) {
        this.discount_name = discount_name;
        this.remarks = remarks;
        this.futsal = futsal;
        this.ground = ground;
    }

    public DiscountMaster(int discount_master_id, String discount_name,String date_from, String date_to, String remarks, String status, Date insert_time,Ground ground) {
        this.ground = ground;
        this.date_from = date_from;
        this.date_to = date_to;
        this.discount_master_id = discount_master_id;
        this.discount_name = discount_name;
        this.remarks = remarks;
        this.status = status;
        this.insert_time = insert_time;
    }

    public DiscountMaster inAnotherFormat() {
       return new DiscountMaster(this.discount_master_id, this.discount_name, this.date_from,this.date_to,this.remarks,this.status,this.insert_time,ground.inGenenericFormat());
    }

    public DiscountMaster() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", date_from='" + date_from + '\'' +
                ", date_to='" + date_to + '\'' +
                ", remarks='" + remarks + '\'' +
                ", status='" + status + '\'' +
                ", insert_time=" + insert_time +
                '}';
    }
}