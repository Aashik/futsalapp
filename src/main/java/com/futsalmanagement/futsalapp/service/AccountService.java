package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

//service interface for account
public interface AccountService {
    Account insert(Account account);
    boolean checkDuplication(String email);
    boolean checkUsernameAvailability(String username);
    Account validatePassword(String username, String password);
    List<Account> getAllAccountOfFutsal(int futsal_id);
    Account getAccountById(int futsal_id, int account_id);
    Account getAccountByUsername(String username);
    void deleteAccount(int account_id);

}
