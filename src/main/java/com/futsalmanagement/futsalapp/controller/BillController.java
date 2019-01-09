package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Bill;
import com.futsalmanagement.futsalapp.entity.Expense;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BillController {

    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;
    @Autowired
    private BillService billService;
    @Autowired
    private ExpenseService expenseService;

//    @RequestMapping(value = "api/createBill", method = RequestMethod.POST)
//    public ResponseEntity<GlobalResponse> createBill(@RequestBody BillRequest billRequest){
//
//        int futsal_id = billRequest.getFutsal_id();
//        int ground_id = billRequest.getGround_id();
//        if (futsalService.checkFutsalAvailability(futsal_id) && groundService.checkGroundAvailability(futsal_id, ground_id)) {
//            Bill to_persist_bill = new Bill();
//            to_persist_bill.setBook_person_name(billRequest.getBook_person_name());
//            to_persist_bill.setPlay_start_time(billRequest.getPlay_start_time());
//            to_persist_bill.setPlay_duration(billRequest.getPlay_duration());
//            to_persist_bill.setFutsal(futsalService.getFutsalById(futsal_id));
//            to_persist_bill.setGround(groundService.getGroundById(futsal_id, ground_id));
//            //to_persist_bill.setTotal_amount(billService.calculateTotalPrice(billRequest));
//           // Map<String, BigDecimal> pricemap = billService.calculateTotalPrice(billRequest);
//          //  to_persist_bill.setPlay_amount(pricemap.get("play_cost"));
//         //   to_persist_bill.setAddition_expense_amount(pricemap.get("additional_cost"));
//
//            Bill createdbill = billService.insert(to_persist_bill);
//            int count = 0;
//            if (createdbill != null) {
//                for (Expense expense : billRequest.getExpenseList()){
//                 //   expense.setBill(createdbill);
//                    Expense expense1 = expenseService.insert(expense);
//                    count = count + (expense1!=null ? 1 : 0);
//                }
//
//                if (billRequest.getExpenseList().size() == count){
//                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "Bill created successfully" , createdbill.inSimpleFormat());
//                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
//                }
//            }
//            GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "cannot create bill. Invalid request" , null);
//            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
//        }
//        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal or ground. Please check" , null);
//        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
//
//    }

    @RequestMapping(value = "api/generateBill" , method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> generateBill(@RequestParam("game_id") int game_id){
        Bill generatedBill = billService.generateBill(game_id);
        if (generatedBill != null){
            List<Expense> expenses = expenseService.getAllExpensesByGame(game_id);
            List<Map<String, Object>> expenselist = expenseService.getInGenericExpense(expenses);
            Map<String,Object> data = new HashMap<>();
            data.put("bill" , generatedBill.inAnotherFormat());
            data.put("expense details" , expenselist);
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, " Bill created Succesfully" , data);
            return new ResponseEntity<GlobalResponse>(response,HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid Operation. Try again" , null);
        return new ResponseEntity<GlobalResponse>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "api/getBillById" , method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getBillById(@RequestParam("bill_id") int bill_id){
        Bill foundBill = billService.getBillById(bill_id);
        if (foundBill != null){
            List<Expense> expenses = expenseService.getAllExpensesByGame(foundBill.getGame().getGame_id());
            List<Map<String, Object>> expenselist = expenseService.getInGenericExpense(expenses);
            Map<String,Object> data = new HashMap<>();
            data.put("bill" , foundBill.inAnotherFormat());
            data.put("expense details" , expenselist);
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, " Bill fetched Succesfully" , data);
            return new ResponseEntity<GlobalResponse>(response,HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "null data fetched, Invalid Request", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }






}
