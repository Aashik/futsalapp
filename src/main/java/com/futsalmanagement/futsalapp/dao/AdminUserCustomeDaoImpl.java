package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminUserCustomeDaoImpl implements AdminUserCustomeDao {

    @Autowired
    private AdminUserCustomeDao adminUserCustomeDao;

    @Override
    public List<AdminUser> getAll() {
        return adminUserCustomeDao.getAll();
    }

    @Override
    public int update(AdminUser adminUser) {
        return adminUserCustomeDao.update(adminUser);
    }
}
