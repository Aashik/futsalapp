package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.AdminUser;
import com.futsalmanagement.futsalapp.entity.Response;
import com.futsalmanagement.futsalapp.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class AdminUserController {



    @RequestMapping(value = "/signup", method= RequestMethod.POST)
    public Response saveUser(@RequestBody AdminUser adminUser){

    }
}
