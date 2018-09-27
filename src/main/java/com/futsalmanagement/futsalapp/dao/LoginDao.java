package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<Login , Integer> {
}
