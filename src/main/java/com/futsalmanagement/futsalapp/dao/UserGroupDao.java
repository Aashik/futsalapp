package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupDao extends JpaRepository<UserGroup, Integer> {
}
