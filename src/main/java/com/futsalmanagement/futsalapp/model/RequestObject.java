package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.entity.Address;

public class RequestObject {

    private int account_id;
    private String fullName;
    private String userName;
    private String email;
    private String contactNo;
    private String password;
    private String futsalName;
    private Address address;
    private int futsal_id;
    private String new_userName;
    private int ground_id;
    private String date;


    public RequestObject(String fullName, String userName, String email, String contactNo, String password, String futsalName, Address address) {
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.futsalName = futsalName;
        this.address = address;
    }

    public RequestObject() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getGround_id() {
        return ground_id;
    }

    public void setGround_id(int ground_id) {
        this.ground_id = ground_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNew_userName() {
        return new_userName;
    }

    public void setNew_userName(String new_userName) {
        this.new_userName = new_userName;
    }

    public int getFutsal_id() {
        return futsal_id;
    }

    public void setFutsal_id(int futsal_id) {
        this.futsal_id = futsal_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFutsalName() {
        return futsalName;
    }

    public void setFutsalName(String futsalName) {
        this.futsalName = futsalName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
