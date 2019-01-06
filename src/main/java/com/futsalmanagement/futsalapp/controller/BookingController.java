package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Booking;
import com.futsalmanagement.futsalapp.entity.Customer;
import com.futsalmanagement.futsalapp.entity.Game;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.model.BookingRequest;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.model.TimeFrame;
import com.futsalmanagement.futsalapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
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
    @Autowired
    private GameService gameService;
    @Autowired
    private CustomerService customerService;

    //create a booking
    @RequestMapping(value = "api/createBooking", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> createBooking(@RequestBody BookingRequest booking) {
        //validate futsal and ground availability
        if (futsalService.checkFutsalAvailability(booking.getFutsal_id()) &&
                groundService.checkGroundAvailability(booking.getFutsal_id(), booking.getGround_id())) {
            //validate timing of the booking
            //check the time of the  booking
            //check if the requested time is available for the particular ground
            if (bookingService.checkIfDateValid(booking.getBooking_date(), booking.getBooking_time()) && bookingService.ifBookTimeAvailable(booking)) {
                Customer foundCustomer = customerService.ifPresentCustomer(booking.getContact_num(), booking.getFutsal_id());
                Customer toAddCustomer = null;
                if (foundCustomer != null) {
                    int play_count = foundCustomer.getCustomer_play_count();
                    foundCustomer.setCustomer_play_count(play_count + 1);
                    toAddCustomer = customerService.addCustomer(foundCustomer);
                } else {
                    Customer newCustomer = new Customer();
                    newCustomer.setFull_name(booking.getFull_name());
                    newCustomer.setContact_number(booking.getContact_num());
                    newCustomer.setEmail(booking.getEmail());
                    newCustomer.setAddress(booking.getAddress());
                    newCustomer.setCustomer_play_count(1);
                    newCustomer.setFutsal(futsalService.getFutsalById(booking.getFutsal_id()));
                    toAddCustomer = customerService.addCustomer(newCustomer);
                }
                if (toAddCustomer != null) {
                    Booking insBooking = new Booking();
                    insBooking.setBooking_code(bookingService.generateBookingCode(booking));
                    insBooking.setCustomer(toAddCustomer);
                    insBooking.setBooking_date(booking.getBooking_date());
                    insBooking.setBooking_time(booking.getBooking_time());
                    insBooking.setBooking_duration(booking.getBooking_duration());
                    insBooking.setBooking_status(Status.PENDING.toString());
                    insBooking.setBookFutsal(futsalService.getFutsalById(booking.getFutsal_id()));
                    insBooking.setBookGround(groundService.getGroundById(booking.getFutsal_id(), booking.getGround_id()));
                    Booking cretedBooking = bookingService.insertBooking(insBooking);
                    if (cretedBooking != null) {
                        GlobalResponse response = new GlobalResponse(Status.SUCCESS, "requested for the booking successfully", cretedBooking.genericFormat());
                        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                    }
                    GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "invalid request. Cannot create booking", null);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid date or time. Please check again", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal or ground id. Please check again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/updateBooking", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> confirmBooking(@RequestBody Map<String, Object> request) {
//        System.out.println("booking code -->> " + booking_code);
//        System.out.println("booking id -->> " + booking_id);
        String booking_code = (String) request.get("booking_code");
        String status = (String) request.get("booking_status");
        System.out.println("booking status" + status);
        Booking foundBooking = bookingService.findBookingById(booking_code);

        System.out.println("BOOKING CONTROLLER -->> found booking " + foundBooking);
        if (foundBooking != null) {
            switch (status) {
                case "1":
                    foundBooking.setBooking_status(Status.CONFIRMED.toString());
                    break;
                case "2":
                    foundBooking.setBooking_status(Status.CANCELLED.toString());
                    break;
                case "3":
                    foundBooking.setBooking_status(Status.GAME_STARTED.toString());
                    Game game = new Game();
                    game.setCustomer(foundBooking.getCustomer());
                    game.setPlay_date(foundBooking.getBooking_date());
                    game.setPlay_start_time(foundBooking.getBooking_time());
                    game.setPlay_duration(foundBooking.getBooking_duration());
                    game.setGame_type(Status.BOOKED.toString());
                    game.setGame_status(Status.STARTED.toString());
                    game.setPlayFutsal(foundBooking.getBookFutsal());
                    game.setPlayGround(foundBooking.getBookGround());
                    gameService.addGame(game);
                    break;
                default:
                    foundBooking.setBooking_status(Status.PENDING.toString());
                    break;
            }
            Booking checkbooking = bookingService.insertBooking(foundBooking);
            if (checkbooking != null) {
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "booking confirmed successfully ", checkbooking.genericFormat());
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "sorry could not confirm booking. Try again[", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid request. Try again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "api/getParticularBooking", method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getParticularBooking(@RequestParam("booking_code") String booking_code) {
        Booking booking = bookingService.findBookingById(booking_code);
        if (booking != null) {
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "booking details retrieved", booking.genericFormat());
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Invalid booking code", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/ground/getAllBooking", method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getAllBooking(@RequestParam("futsal_id") int futsal_id) {

        List<Booking> bookingList = bookingService.getAllBooking(futsal_id);
        List<Booking> bookingListInGeneric = bookingList.stream().map(booking -> booking.genericFormat()).collect(Collectors.toList());
        if (bookingList != null && bookingList.size() > 0) {
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "all the booking retrieved", bookingListInGeneric);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "no bookings available", bookingList);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "api/searchAvailableBooking", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> fetchAvailableBooking(@RequestBody Map<String, Object> requestObject) {
        List<Map<String, Object>> booktimeforAllGround = new ArrayList<>();
        int futsal_id = Integer.parseInt(requestObject.get("futsal_id").toString());
        String to_book_date = requestObject.get("to_book_date").toString();
        //check if valid date. should not be able to search for booking for past dates
        boolean test = bookingService.ifPastDate(to_book_date);
        System.out.println("is pastdate test  --->>>> " + test);
        if (!bookingService.ifPastDate(to_book_date) || gameService.checkifTodayDate(to_book_date)){
            for (Ground ground : groundService.getGoundListByFutsal(futsal_id)) {
                List<TimeFrame> availablebooktime = bookingService.getAvailableTimeToBook(futsal_id, to_book_date, ground.getGround_id());
                Map<String, Object> booktimeresponse = new HashMap<>();
                booktimeresponse.put("available_time", bookingService.filterOutPastTime(availablebooktime,to_book_date));
                booktimeresponse.put("ground_id", ground.getGround_id());
                booktimeresponse.put("ground_name", ground.getGround_name());
                booktimeresponse.put("booking_date", to_book_date);
                booktimeforAllGround.add(booktimeresponse);
            }

            if (booktimeforAllGround.size() > 0 && !booktimeforAllGround.isEmpty()) {
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "Available booking time", booktimeforAllGround);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
        }

        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Sorry invalid request/Date time error", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

}
