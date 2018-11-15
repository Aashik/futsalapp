package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //it will be in percent
    private int discount_margin;
    private int[] discount_weekdays;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
    @ManyToOne
    @JoinColumn(name = "ground_id")
    private Ground ground;

    public Discount(@Size(max = 3) int discount_margin, int[] discount_weekdays, Futsal futsal, Ground ground) {
        this.discount_margin = discount_margin;
        this.discount_weekdays = discount_weekdays;
        this.futsal = futsal;
        this.ground = ground;
    }

    public Discount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiscount_margin() {
        return discount_margin;
    }

    public void setDiscount_margin(int discount_margin) {
        this.discount_margin = discount_margin;
    }

    public int[] getDiscount_weekdays() {
        return discount_weekdays;
    }

    public void setDiscount_weekdays(int[] discount_weekdays) {
        this.discount_weekdays = discount_weekdays;
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
        return "Discount{" +
                "id=" + id +
                ", discount_margin=" + discount_margin +
                ", discount_weekdays=" + Arrays.toString(discount_weekdays) +
                '}';
    }
}
