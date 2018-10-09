package com.futsalmanagement.futsalapp.model;

import com.futsalmanagement.futsalapp.entity.Address;

public class RequestObject {

    private String fullName;
    private String userName;
    private String email;
    private String contactNo;
    private String password;
    private String futsalName;
    private Address address;
    private int futsal_id;

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
