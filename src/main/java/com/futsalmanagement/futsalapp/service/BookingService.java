package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.model.BookingRequest;

import java.text.ParseException;
import java.util.List;

public interface BookingService {

    Booking insertBooking(Booking book);
    boolean checkIfDateValid(BookingRequest booking);
    boolean ifBookTimeAvailable(BookingRequest booking);
    String generateBookingCode(BookingRequest booking);
    Booking findBookingById(String booking_code, int booking_id);
    List<Booking> getAllBooking(int futsal_id, int ground_id);



}
