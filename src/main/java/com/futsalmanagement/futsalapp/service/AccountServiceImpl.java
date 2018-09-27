package com.futsalmanagement.futsalapp.service;


import com.futsalmanagement.futsalapp.dao.AccountDao;
import com.futsalmanagement.futsalapp.entity.Account;
import com.futsalmanagement.futsalapp.utility.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
