package com.futsalmanagement.futsalapp.controller;


import com.futsalmanagement.futsalapp.dao.LoginDao;
import com.futsalmanagement.futsalapp.entity.Account;
import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.entity.Login;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.LoginRequest;
import com.futsalmanagement.futsalapp.model.RequestObject;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.AccountService;
import com.futsalmanagement.futsalapp.service.FutsalService;
import com.futsalmanagement.futsalapp.service.LoginService;
import com.futsalmanagement.futsalapp.service.UserGroupService;
import com.futsalmanagement.futsalapp.utility.PasswordHash;
import com.futsalmanagement.futsalapp.utility.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private FutsalService futsalService;

    //this api will be user only by the developers team to create the initial user for the futsal owner
    //after that the rest application management will be done by the owner and respective employee

    @RequestMapping(value = "/api/admin/signup", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> createAccount(@RequestBody RequestObject requestObject) {
        //checkduplication
        String email = requestObject.getEmail();
        boolean checkparam = accountService.checkDuplication(email);
        boolean checkusername = accountService.checkUsernameAvailability(requestObject.getUserName());
        if (!checkparam && !checkusername) {
            //check if password is min 8 character

            if (requestObject.getPassword().length() >= 8) {
                //password hashing
                String encyptedPassword = PasswordHash.getpasswordhash(requestObject.getPassword(), requestObject.getContactNo());
                Account toBeInsertedAccount = new Account();
                toBeInsertedAccount.setUserName(requestObject.getUserName());
                toBeInsertedAccount.setFullName(requestObject.getFullName());
                toBeInsertedAccount.setEmail(email);
                toBeInsertedAccount.setContactNo(requestObject.getContactNo());
                toBeInsertedAccount.setPassword(encyptedPassword);
                toBeInsertedAccount.setUserGroup(userGroupService.getOwner());
                //Account insertAcc = accountService.insert(toBeInsertedAccount);
                toBeInsertedAccount.setStatus(true);
                Futsal futsal = new Futsal();
                futsal.setFutsal_name(requestObject.getFutsalName());
                futsal.setAddress(requestObject.getAddress());
                futsal.setContact_no(requestObject.getContactNo());
                futsal.setEmail(requestObject.getEmail());
                futsal.setImage_url("test");
                futsal.setMobile_no("test");
                Futsal insFutsal = futsalService.insert(futsal);
                if (insFutsal != null) {
                    toBeInsertedAccount.setFutsal(insFutsal);
                    Account insertAcc = accountService.insert(toBeInsertedAccount);
                    if (insertAcc != null) {
                        GlobalResponse response = new GlobalResponse(Status.SUCCESS, "account created succesfully", insertAcc);
                        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                    }
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "invalid Request. cannot perform action", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "password must be more than 8 character", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "duplicate email or username. use another email", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/admin/login", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> login(@RequestBody LoginRequest loginRequest) {
        //check if the username is available or not
        boolean usernamePresent = accountService.checkUsernameAvailability(loginRequest.getUsername());
        if (usernamePresent) {
            Account validateUser = accountService.validatePassword(loginRequest.getUsername(), loginRequest.getPassword());
            if (validateUser != null) {
                Login login = new Login();
                String currenttime = new Timestamp(System.currentTimeMillis()).toString();
                String datatohash = loginRequest.getUsername() + currenttime;
                String login_token = TokenGeneration.getrandomtoken(datatohash);
                login.setLogin_token_status(true);
                login.setLoginToken(login_token);
                login.setAccount(validateUser);
                login.setUserName(loginRequest.getUsername());
                String userGroup = validateUser.getUserGroup().getUser_group_name().equals("OWNER") ? "OWNER" : "EMPLOYEE";

                Login loginInstance = loginService.insert(login);

                if (loginInstance != null) {
                    Map<String, Object> loginresponse = new HashMap<>();
                    loginresponse.put("login_user_group", userGroup);
                    loginresponse.put("futsal_id", validateUser.getFutsal().getFutsal_id());
                    loginresponse.put("login_isntance", login);

                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "logged in successfully", loginresponse);

                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }

                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Invalid request. cannot perform action", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "invalid login credentials. Please check again", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Username not present. Please check again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

    }


    @RequestMapping(value = "api/admin/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addEmployee(@RequestBody RequestObject requestObject){

        String email = requestObject.getEmail();
        boolean checkparam = accountService.checkDuplication(email);
        boolean checkusername = accountService.checkUsernameAvailability(requestObject.getUserName());
        if (!checkparam && !checkusername) {

            if (requestObject.getPassword().length() >= 8) {
                String encyptedPassword = PasswordHash.getpasswordhash(requestObject.getPassword(), requestObject.getContactNo());


            Account toAddEmployee = new Account();
            toAddEmployee.setUserName(requestObject.getUserName());
            toAddEmployee.setFullName(requestObject.getFullName());
            toAddEmployee.setContactNo(requestObject.getContactNo());
            toAddEmployee.setEmail(requestObject.getEmail());
            toAddEmployee.setPassword(encyptedPassword);
            toAddEmployee.setUserGroup(userGroupService.getEmployee());
            toAddEmployee.setFutsal(futsalService.getFutsalById(requestObject.getFutsal_id()));
            toAddEmployee.setStatus(true);

            Account insertedAccount = accountService.insert(toAddEmployee);

            if (insertedAccount != null){
                GlobalResponse response = new GlobalResponse(Status.SUCCESS, "account created succesfully", insertedAccount);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "invalid Request. cannot perform action", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

        }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "password must be more than 8 character", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);


        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "duplicate email or username. use another email", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);


    }

    @RequestMapping(value = "api/admin/getAllEmployee" , method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getAllEmployee(@RequestParam("futsal_id") int futsal_id){

        if(futsalService.checkFutsalAvailability(futsal_id)){

            List<Account> employeeaccountlist = accountService.getAllAccountOfFutsal(futsal_id);
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "All employee retrieved" , employeeaccountlist);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid futsal id. Try again" , null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


}
