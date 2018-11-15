package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountDao extends JpaRepository<Discount, Integer> {
}
