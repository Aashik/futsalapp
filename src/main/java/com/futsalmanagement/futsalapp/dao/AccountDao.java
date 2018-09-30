package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Long>{
}
