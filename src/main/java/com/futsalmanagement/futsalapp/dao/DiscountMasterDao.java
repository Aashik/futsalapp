package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.DiscountMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountMasterDao extends JpaRepository<DiscountMaster , Integer> {
}
