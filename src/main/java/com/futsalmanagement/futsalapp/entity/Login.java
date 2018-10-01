package com.futsalmanagement.futsalapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loginId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    private String userName;
    @Column(name="login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;
    private String loginToken;
    private boolean login_token_status;


    public Login() {
    }

    public Login(int loginId, Account account, String userName, Date loginTime, String loginToken, boolean login_token_status) {
        this.loginId = loginId;
        this.account = account;
        this.userName = userName;
        this.loginTime = loginTime;
        this.loginToken = loginToken;
        this.login_token_status = login_token_status;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public boolean isLogin_token_status() {
        return login_token_status;
    }

    public void setLogin_token_status(boolean login_token_status) {
        this.login_token_status = login_token_status;
    }
}
