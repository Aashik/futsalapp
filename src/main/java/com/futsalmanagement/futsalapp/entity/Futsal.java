package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Futsal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int futsal_id;
    private String futsal_name;
    private String contact_no;
    private String mobile_no;
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;
    private String email;
    private String image_url;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "futsal")
    private Set<Ground> ground;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "futsal")
    private Set<Account> account;
//    @JsonManagedReference
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "futsal")
//    private Set<Booking> bookings;





    public Futsal(int futsal_id, String contact_no, String mobile_no, Address address, String email, String image_url) {
        this.futsal_id = futsal_id;
        this.contact_no = contact_no;
        this.mobile_no = mobile_no;
        this.address = address;
        this.email = email;
        this.image_url = image_url;
    }

    public Futsal() {
    }

    public Set<Ground> getGround() {
        return ground;
    }

    public void setGround(Set<Ground> ground) {
        this.ground = ground;
    }

    public Set<Account> getAccount() {
        return account;
    }

    public void setAccount(Set<Account> account) {
        this.account = account;
    }

//    public Set<Booking> getBookings() {
//        return bookings;
//    }
//
//    public void setBookings(Set<Booking> bookings) {
//        this.bookings = bookings;
//    }

    public String getFutsal_name() {
        return futsal_name;
    }

    public void setFutsal_name(String futsal_name) {
        this.futsal_name = futsal_name;
    }

    public int getFutsal_id() {
        return futsal_id;
    }

    public void setFutsal_id(int futsal_id) {
        this.futsal_id = futsal_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Futsal{" +
                "futsal_id=" + futsal_id +
                ", futsal_name='" + futsal_name + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
