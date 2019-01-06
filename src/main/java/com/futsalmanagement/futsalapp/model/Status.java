package com.futsalmanagement.futsalapp.model;

public enum  Status {
    SUCCESS, DATA_ERROR,SYSTEM_ERROR,

    PENDING, CONFIRMED, CANCELLED,GAME_STARTED,

    STARTED, COMPLETED,

    DIRECT_ENTRY, BOOKED,

    //for discounts
    ACTIVE,CURRENT,INACTIVE;
}
