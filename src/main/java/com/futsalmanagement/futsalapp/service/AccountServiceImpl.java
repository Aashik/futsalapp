package com.futsalmanagement.futsalapp.service;


import com.futsalmanagement.futsalapp.dao.AccountDao;
import com.futsalmanagement.futsalapp.entity.Account;
import com.futsalmanagement.futsalapp.utility.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account insert(Account account) {
        Account a = accountDao.save(account);
        return (a != null && a.getUserName().equals(account.getUserName()))? a: null;
    }

    @Override
    public boolean checkDuplication(String  toCheckEmail) {
        List<Account> accountlist = accountDao.findAll();
        Account foundAccount = accountlist.stream()
                .filter(account -> toCheckEmail.equals(account.getEmail())).findAny().orElse(null);
         return foundAccount == null ? false : true;
    }

    @Override
    public boolean checkUsernameAvailability(String username) {
        List<Account> accountList = accountDao.findAll();
        Account foundAccount = accountList.stream()
                .filter(account -> username.equals(account.getUserName())).findAny().orElse(null);

        return foundAccount == null?false:true;
    }

    @Override
    public Account validatePassword(String username, String password) {
        List<Account> accountList = accountDao.findAll();
        Account foundAccount = accountList.stream()
                .filter(account -> username.equals(account.getUserName())).findAny().orElse(null);

        String toCheckpassword = foundAccount.getPassword();
        String givenPassword = PasswordHash.getpasswordhash(password, foundAccount.getContactNo());

        return  (givenPassword.equals(toCheckpassword)) ? foundAccount : null;
    }

    @Override
    public List<Account> getAllAccountOfFutsal(int futsal_id) {
        List<Account> foundAccountlist = accountDao.findAll().stream()
                .filter(account -> account.getFutsal().getFutsal_id() == futsal_id && account.getUserGroup().getUser_group_id() == 2)
                .collect(Collectors.toList());
        return foundAccountlist.stream()
                .map(account -> account.toEmployeeFormat()).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(int account_id) {
        accountDao.deleteById(account_id);
    }


    @Override
    public Account getAccountByUsername(String username) {
        Account foundAccount = accountDao.findAll().stream()
                .filter(account -> account.getUserName().equals(username)).findAny().orElse(null);
        return foundAccount;
    }


}
