package com.futsalmanagement.futsalapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
public class Account extends Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String fullName;
    private String email;
    private String contactNo;
    private String password;
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private UserGroup userGroup;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "futsal_id")
    private Futsal futsal;

    public Account() {
    }

    public Account(String userName, String fullName, String email, String contactNoo, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
    }

    public Account(int id, String userName, String fullName, String email, String contactNo,boolean status,UserGroup userGroup){
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.contactNo = contactNo;
        this.status = status;
        this.userGroup = userGroup;
    }

    public Futsal getFutsal() {
        return futsal;
    }

    public void setFutsal(Futsal futsal) {
        this.futsal = futsal;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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


    public Account toEmployeeFormat() {
        return new Account(this.id, this.userName, this.fullName,this.email,this.contactNo,this.status, this.userGroup);
    }
}
