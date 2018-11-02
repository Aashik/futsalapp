package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.model.BookingRequest;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.BookingService;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.futsalmanagement.futsalapp.service.GroundService;
import com.sun.jmx.snmp.SnmpUnknownAccContrModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;
    @Autowired
    private BookingService bookingService;

    //create a booking
    @RequestMapping(value = "api/createBooking", method = RequestMethod.POST)

    public ResponseEntity<GlobalResponse> createBooking(@RequestBody BookingRequest booking) {
        //validate futsal and ground availability
        if (futsalService.checkFutsalAvailability(booking.getFutsal_id()) &&
                groundService.checkGroundAvailability(booking.getFutsal_id(), booking.getGround_id())) {
            //validate timing of the booking
            //check the time of the  booking
            //check if the requested time is available for the particular ground
            boolean checkdate = bookingService.checkIfDateValid(booking);
            boolean checktime = bookingService.ifBookTimeAvailable(booking);
            System.out.println("CONTROLLER " + "check date " + checkdate + " check time " + checktime);
            if (bookingService.checkIfDateValid(booking) && bookingService.ifBookTimeAvailable(booking)) {

                Booking insBooking = new Booking();
                insBooking.setBooking_code(bookingService.generateBookingCode(booking));
                insBooking.setFullname(booking.getFull_name());
                insBooking.setContact_num(booking.getContact_num());
                insBooking.setBooking_date(booking.getBooking_date());
                insBooking.setBooking_time(booking.getBooking_time());
                insBooking.setBooking_duration(booking.getBooking_duration());
                insBooking.setBooking_status(Status.PENDING.toString());
                insBooking.setBookFutsal(futsalService.getFutsalById(booking.getFutsal_id()));
                insBooking.setBookGround(groundService.getGroundById(booking.getFutsal_id(), booking.getGround_id()));

                Booking cretedBooking = bookingService.insertBooking(insBooking);
                if (cretedBooking != null) {
                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "requested for the booking successfully", cretedBooking);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "invalid request. Cannot create booking", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid date or time. Please check again", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal or ground id. Please check again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/confirmBooking" , method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> confirmBooking(@RequestParam("booking_code") String booking_code,
                                                          @RequestParam("booking_id") int booking_id){
        System.out.println("booking code -->> " + booking_code);
        System.out.println("booking id -->> " + booking_id);
        Booking  foundBooking = bookingService.findBookingById(booking_code,booking_id);
        System.out.println("BOOKING CONTROLLER -->> found booking " + foundBooking);
        if (foundBooking != null){
         foundBooking.setBooking_status(Status.CONFIRMED.toString());
         Booking checkbooking = bookingService.insertBooking(foundBooking);
         if (checkbooking != null){
             GlobalResponse response = new GlobalResponse(Status.SUCCESS, "booking confirmed successfully ", checkbooking.genericFormat());
             return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
         }
            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "sorry could not confirm booking. Try again[", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid request. Try again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    //cancel a booking

    @RequestMapping(value = "api/cancelBooking", method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> cancelBooking(@RequestParam("booking_code") String booking_code,
                                                        @RequestParam("booking_id") int booking_id){
        Booking  foundBooking = bookingService.findBookingById(booking_code,booking_id);
        if (foundBooking != null){
            foundBooking.setBooking_status(Status.CANCELLED.toString());
            Booking checkbooking = bookingService.insertBooking(foundBooking);
            if (checkbooking != null){
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "booking cancelled successfully ", checkbooking.genericFormat());
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "sorry could not cancel booking. Try again[", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid request. Try again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/ground/getAllBooking", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> getAllBooking(@RequestBody Map<Object,Object> request){
        int futsal_id = Integer.parseInt(request.get("futsal_id").toString());
        int ground_id = Integer.parseInt(request.get("ground_id").toString());
        List<Booking> bookingList = bookingService.getAllBooking(futsal_id,ground_id);
        List<Booking> bookingListInGeneric = bookingList.stream().map(booking -> booking.genericFormat()).collect(Collectors.toList());
        if (bookingList !=  null && bookingList.size() > 0 ){
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "all the booking retrieved", bookingListInGeneric);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "no bookings available", bookingList);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }
}
