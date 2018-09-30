package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Account;

public interface AccountCustomeDao {

    boolean isEmpty(Account account);
    boolean checkDuplicate(String email);
    Account getByUserName(String username);
    int update(Account a);
}
