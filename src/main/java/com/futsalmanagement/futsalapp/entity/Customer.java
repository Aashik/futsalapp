package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    @NotNull
    @Size(max = 50)
    private String full_name;
    @NotNull
    @Size(max = 15)
    private String contact_number;
    private String address;
    private String email;
    private int customer_play_count;
    @ManyToOne
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;

    public Customer(@NotNull @Size(max = 50) String full_name, @NotNull @Size(max = 15) String contact_number, String address, String email) {
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.address = address;
        this.email = email;
    }

    public Customer inGenericFOrmat(){
        return new Customer(this.full_name,this.contact_number,this.address,this.email);
    }

    public Customer() {
    }

    public int getCustomer_play_count() {
        return customer_play_count;
    }

    public void setCustomer_play_count(int customer_play_count) {
        this.customer_play_count = customer_play_count;
    }

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", full_name='" + full_name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
