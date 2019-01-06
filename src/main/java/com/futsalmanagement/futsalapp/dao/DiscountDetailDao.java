package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.DiscountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountDetailDao extends JpaRepository<DiscountDetail, Integer> {
}
