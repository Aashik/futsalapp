package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.AdminUser;

import java.util.List;

public interface AdminUserCustomeDao {
    List<AdminUser> getAll();
    int update (AdminUser adminUser);
}
