package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Ground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroundDao extends JpaRepository<Ground, Integer> {
}
