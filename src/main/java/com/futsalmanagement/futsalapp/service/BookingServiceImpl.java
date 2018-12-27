package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.BookingDao;
import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.model.BookingRequest;
import com.futsalmanagement.futsalapp.model.TimeFrame;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao bookingDao;
    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;

    @Override
    public Booking insertBooking(Booking book) {
        Booking booking = bookingDao.save(book);
        return booking;
    }

    @Override
    public boolean checkIfDateValid(String bookingdate, String bookingtime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bookdate = formatter.parse(bookingdate + " " + bookingtime+":00");
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
    public Booking findBookingById(String booking_code) {
        Booking foundbooking = bookingDao.findAll().stream()
                .filter(booking ->  booking_code.equals(booking.getBooking_code()))
                .findAny().orElse(null);
        return foundbooking;
    }


    @Override
    public List<Booking> getAllBooking(int futsal_id) {
        List<Booking> bookingListofAFutstal = bookingDao.findAll().stream().filter(booking -> futsal_id == booking.getBookFutsal().getFutsal_id()).collect(Collectors.toList());
        return bookingListofAFutstal;
    }

    private Timestamp converToTimeStamp(String bookingDate, String bookingTime) {
        try {
            String bookingTimeConcat = bookingDate.trim() + " " + bookingTime.trim() + ":00";
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
        for (int i = 0; i < tokens.length; i++) {
            sb.append(tokens[i].toUpperCase().charAt(0));
        }
        String initials = sb.toString();
        return initials + "-" + temp;
    }


    @Override
    public List<TimeFrame> getAvailableTimeToBook(int futsal_id, String to_book_date, int ground_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<Booking> bookedTimeForTheDay = bookingDao.findAll().stream()
                .filter(booking -> futsal_id == booking.getBookFutsal().getFutsal_id() &&
                        to_book_date.equals(booking.getBooking_date()) && ground_id == booking.getBookGround().getGround_id()).collect(Collectors.toList());
        Collections.sort(bookedTimeForTheDay, new Comparator<Booking>() {
            @Override
            public int compare(Booking b1, Booking b2) {
                return converToTimeStamp(b1.getBooking_date(), b1.getBooking_time()).compareTo(converToTimeStamp(b2.getBooking_date(), b2.getBooking_time()));
            }
        });
        List<TimeFrame> availableBookTimes = new ArrayList<>();
        Timestamp groundopeningtime = converToTimeStamp(to_book_date, groundService.getGroundById(futsal_id, ground_id).getOpening_hour());
        Timestamp afterHalfHour = new Timestamp(groundopeningtime.getTime() + (30 * 60 * 1000));
        Timestamp starttime = groundopeningtime;

        for (Booking b : bookedTimeForTheDay) {
            Timestamp booktime = converToTimeStamp(b.getBooking_date(), b.getBooking_time());
            Timestamp bookendtime = new Timestamp(booktime.getTime() + (long)(b.getBooking_duration() * 60 * 60 * 1000));
            while (afterHalfHour.before(booktime)) {
                afterHalfHour = new Timestamp(afterHalfHour.getTime() + (30 * 60 * 1000));
            }
            TimeFrame timeFrame = new TimeFrame();
            Date availstarttime = new Date(starttime.getTime());
            Date availendtime = new Date(afterHalfHour.getTime());

            timeFrame.setStart_time(sdf.format(availstarttime));
            timeFrame.setEnd_time(sdf.format(availendtime));
            availableBookTimes.add(timeFrame);
            starttime = bookendtime;
            afterHalfHour = new Timestamp(bookendtime.getTime() + (30 * 60 * 1000));
        }

        Date laststarttime = new Date(starttime.getTime());
        Date lastendtime = new Date(converToTimeStamp(to_book_date, groundService.getGroundById(futsal_id,ground_id).getClosing_hour()).getTime());
        if ((lastendtime.getTime() - laststarttime.getTime()) >= (1*60*60*1000)){
            TimeFrame lasttimeframe = new TimeFrame();
            lasttimeframe.setStart_time(sdf.format(laststarttime));
            lasttimeframe.setEnd_time(sdf.format(lastendtime));
            availableBookTimes.add(lasttimeframe);
        }
        return availableBookTimes;
    }


}
