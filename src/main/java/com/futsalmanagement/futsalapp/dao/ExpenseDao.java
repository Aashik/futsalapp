package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseDao extends JpaRepository<Expense,Integer>{
}
