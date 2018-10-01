package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutsalDao extends JpaRepository<Futsal, Integer>{


}
