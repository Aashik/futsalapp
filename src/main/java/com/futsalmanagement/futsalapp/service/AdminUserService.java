package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.AdminUser;

import java.util.List;

public interface AdminUserService {
    List<AdminUser> getAll();
    int update(AdminUser adminUser);

}
