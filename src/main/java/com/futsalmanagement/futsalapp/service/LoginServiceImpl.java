package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.LoginDao;
import com.futsalmanagement.futsalapp.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public Login insert(Login login) {
       Login l =  loginDao.save(login);
       return (l != null && l.getUserName().equals(login.getUserName())) ? l : null;
    }
}
