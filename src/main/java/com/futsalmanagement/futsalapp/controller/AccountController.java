package com.futsalmanagement.futsalapp.controller;


import com.futsalmanagement.futsalapp.dao.LoginDao;
import com.futsalmanagement.futsalapp.entity.Account;
import com.futsalmanagement.futsalapp.entity.Login;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.LoginRequest;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.AccountService;
import com.futsalmanagement.futsalapp.service.LoginService;
import com.futsalmanagement.futsalapp.utility.PasswordHash;
import com.futsalmanagement.futsalapp.utility.TokenGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@RestController
@CrossOrigin
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/api/admin/signup", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> createAccount(@RequestBody Account account) {
        //checkduplication
        String email = account.getEmail();
        boolean checkparam = accountService.checkDuplication(email);
        if (!checkparam) {
            //check if password is min 8 character
            System.out.println("password ---->>>>> " + account.getPassword());
            if (account.getPassword().length() >= 8) {
                //password hashing
                String encyptedPassword = PasswordHash.getpasswordhash(account.getPassword(), account.getContactNo());
                account.setPassword(encyptedPassword);
                Account insertAcc = accountService.insert(account);
                if (insertAcc != null) {
                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "account created succesfully", insertAcc);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "invalid Request. cannot perform action", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }

            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "password must be more than 8 character", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "duplicate email. use another email", null);
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

                Login loginInstance = loginService.insert(login);

                if (loginInstance != null) {
                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "logged in successfully", login);

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


}
