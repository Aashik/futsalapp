package com.futsalmanagement.futsalapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String fullName;
    private String email;
    private int contact_no;
    private String password;

    public AdminUser(String userName, String fullName, String email, int contact_no, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.contact_no = contact_no;
        this.password = password;
    }

    public AdminUser(Long id) {
        this.id = id;
    }

    public AdminUser() {
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

    public int getContact_no() {
        return contact_no;
    }

    public void setContact_no(int contact_no) {
        this.contact_no = contact_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
