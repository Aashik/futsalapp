package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {
}
