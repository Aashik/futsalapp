package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.UserGroup;

public interface UserGroupService{

    UserGroup getOwner();
    UserGroup getEmployee();

}
