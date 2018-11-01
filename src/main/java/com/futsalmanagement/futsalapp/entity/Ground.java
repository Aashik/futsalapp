package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Ground {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ground_id;
    @Size(max = 100)
    private String ground_name;
    @Size(max = 100)
    private String opening_hour;
    @Size(max = 100)
    private String closing_hour;
    private BigDecimal unit_hour_price;
    @Size(max = 100)
    private String image;
    @Size(max = 100)
    private String status;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "ground")
//    private Set<Booking> bookings;


    public Ground() {
    }

//    public Set<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Set<Booking> bookings) {
//        this.bookings = bookings;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGround_id() {
        return ground_id;
    }

    public void setGround_id(int ground_id) {
        this.ground_id = ground_id;
    }

    public String getGround_name() {
        return ground_name;
    }

    public void setGround_name(String ground_name) {
        this.ground_name = ground_name;
    }

    public String getOpening_hour() {
        return opening_hour;
    }

    public void setOpening_hour(String opening_hour) {
        this.opening_hour = opening_hour;
    }

    public String getClosing_hour() {
        return closing_hour;
    }

    public void setClosing_hour(String closing_hour) {
        this.closing_hour = closing_hour;
    }

    public BigDecimal getUnit_hour_price() {
        return unit_hour_price;
    }

    public void setUnit_hour_price(BigDecimal unit_hour_price) {
        this.unit_hour_price = unit_hour_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    @Override
    public String toString() {
        return "Ground{" +
                "ground_id=" + ground_id +
                ", ground_name='" + ground_name + '\'' +
                ", opening_hour='" + opening_hour + '\'' +
                ", closing_hour='" + closing_hour + '\'' +
                ", unit_hour_price=" + unit_hour_price +
                ", image='" + image + '\'' +
                ", futsal=" + futsal +
                '}';
    }
}
