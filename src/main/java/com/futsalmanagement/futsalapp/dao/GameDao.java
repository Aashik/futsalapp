package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<Game, Integer> {
}
