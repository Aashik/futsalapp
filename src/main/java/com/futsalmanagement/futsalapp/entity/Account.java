package com.futsalmanagement.futsalapp.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String user_name;
    private String password;
    private String email;
    private Date time_stamp;
    private boolean account_status;

    public Account() {
    }

    public Account(String user_name, String password, String email, Date time_stamp, boolean account_status) {
        this.user_name = user_name;
        this.password = password;
        this.email = email;
        this.time_stamp = time_stamp;
        this.account_status = account_status;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount(Long account_id) {
        this.account_id = account_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time_stamp = time_stamp;
    }

    public boolean isAccount_Status() {
        return account_status;
    }

    public void setAccount_Status(boolean account_Status) {
        this.account_status = account_Status;
    }
}
