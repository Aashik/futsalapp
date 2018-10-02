package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Futsal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int futsal_id;
    @Size(max = 100)
    private String futsal_name;
    @Size(max = 50)
    private String futsal_code;
    @Size(max = 15)
    private String contact_no;
    @Size(max = 15)
    private String mobile_no;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id" )
    private Address address;
    @Size(max = 100)
    private String email;
    @Size(max = 100)
    private String image_url;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "futsal")
    private Set<Ground> ground;



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

    public String getFutsal_code() {
        return futsal_code;
    }

    public void setFutsal_code(String futsal_code) {
        this.futsal_code = futsal_code;
    }

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
                ", futsal_code='" + futsal_code + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
