package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.BookingDao;
import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.model.BookingRequest;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private FutsalService futsalService;

    @Override
    public Booking insertBooking(Booking book) {
        Booking booking = bookingDao.save(book);
        return booking;
    }

    @Override
    public boolean checkIfDateValid(BookingRequest booking) {
        try {
            String bookingdate = booking.getBooking_date().trim();
            String bookingtime = booking.getBooking_time().trim();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bookdate = formatter.parse(bookingdate + " " + bookingtime);
            DateTime dtofBook = new DateTime(bookdate);
            Date dt = new Date();
            DateTime dtOrg = new DateTime(dt);
            DateTime dtplus2 = dtOrg.plusDays(2);
            //boolean test = dtofBook.isAfter(dtplus2);
            return dtofBook.isBefore(dtplus2) && dtofBook.isAfter(dtOrg);
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public boolean ifBookTimeAvailable(BookingRequest booking) {
        List<Booking> bookingList = bookingDao.findAll().stream().filter(eachbooking ->
                booking.getFutsal_id() == eachbooking.getBookFutsal().getFutsal_id() &&
                        booking.getGround_id() == eachbooking.getBookGround().getGround_id()).collect(Collectors.toList());
        Timestamp bookRequestTime = converToTimeStamp(booking.getBooking_date(), booking.getBooking_time());
        for (Booking booking1 : bookingList) {
            long duration = (long) (booking1.getBooking_duration() * 60 * 60) * 1000;
            long timeAfter = (converToTimeStamp(booking1.getBooking_date(), booking1.getBooking_time()).getTime() + duration);
            Timestamp timeAterDuration = new Timestamp(timeAfter);

            Timestamp starttime = converToTimeStamp(booking1.getBooking_date(), booking1.getBooking_time());
            boolean checkstarttime = bookRequestTime.after(starttime) || bookRequestTime.equals(starttime);
            boolean checkendtime = bookRequestTime.before(timeAterDuration);
            System.out.println("check after time " + checkstarttime + " check before time " + checkendtime);
            if (checkstarttime && checkendtime)
                return false;
        }
        return true;
    }

    @Override
    public Booking findBookingById(String booking_code, int booking_id) {
        Booking foundbooking = bookingDao.findAll().stream()
                .filter(booking -> booking_id == booking.getBooking_id() && booking_code.equals(booking.getBooking_code()))
                .findAny().orElse(null);
        return foundbooking;
    }



    @Override
    public List<Booking> getAllBooking(int futsal_id, int ground_id) {
        List<Booking> bookingListofAFutstal = bookingDao.findAll().stream().filter(booking -> futsal_id == booking.getBookFutsal().getFutsal_id() &&
                                             ground_id == booking.getBookGround().getGround_id()).collect(Collectors.toList());
        return bookingListofAFutstal;
    }

    private Timestamp converToTimeStamp(String bookingDate, String bookingTime) {
        try {

            String bookingTimeConcat = bookingDate.trim() + " " + bookingTime.trim();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date booktime = formatter.parse(bookingTimeConcat);
            Timestamp booktimestamp = new Timestamp(booktime.getTime());
            return booktimestamp;
        } catch (ParseException e) {
            System.out.println("ERROR -->> " + e.getMessage());
            return null;
        }
    }

    @Override
    public String generateBookingCode(BookingRequest booking) {
        Random random = new Random();
        int temp = random.nextInt(10000);
        String futsalname = futsalService.getFutsalById(booking.getFutsal_id()).getFutsal_name();
//        String futsalname = booking.getBookFutsal().getFutsal_name();
        String[] tokens = futsalname.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< tokens.length ; i++){
            sb.append(tokens[i].toUpperCase().charAt(0));
        }
        String initials = sb.toString();
        return  initials + "-" + temp;
    }
}
