package com.futsalmanagement.futsalapp.dao;

import com.futsalmanagement.futsalapp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer>{

}
