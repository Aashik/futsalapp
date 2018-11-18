package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.model.BookingRequest;
import com.futsalmanagement.futsalapp.model.TimeFrame;

import java.text.ParseException;
import java.util.List;

public interface BookingService {

    Booking insertBooking(Booking book);
    boolean checkIfDateValid(BookingRequest booking);
    boolean ifBookTimeAvailable(BookingRequest booking);
    String generateBookingCode(BookingRequest booking);
    Booking findBookingById(String booking_code);
    List<Booking> getAllBooking(int futsal_id);
    List<TimeFrame> getAvailableTimeToBook(int futsal_id, String to_book_date, int ground_id);




}
