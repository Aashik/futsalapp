package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int address_id;
    @Size(max = 50)
    private String street;
    @Size(max = 50)
    private String city;
    @Size(max = 50)
    private String state_district;
    @Size(max = 50)
    private String country;
    private long zip_postal_code;
    @JsonManagedReference
    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Futsal futsal;

    public Address(int address_id, String street, String city, String state_district, String country, long zip_postal_code) {
        this.address_id = address_id;
        this.street = street;
        this.city = city;
        this.state_district = state_district;
        this.country = country;
        this.zip_postal_code = zip_postal_code;
    }

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public Address() {
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_district() {
        return state_district;
    }

    public void setState_district(String state_district) {
        this.state_district = state_district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getZip_postal_code() {
        return zip_postal_code;
    }

    public void setZip_postal_code(long zip_postal_code) {
        this.zip_postal_code = zip_postal_code;
    }
}
