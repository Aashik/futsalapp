package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDao extends JpaRepository<Bill, Integer> {
}
