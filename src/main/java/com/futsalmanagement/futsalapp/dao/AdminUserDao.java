package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserDao extends JpaRepository<AdminUser, Long> {
}
