package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Ground;

import java.util.List;

public interface GroundService  {
    Ground insert(Ground g);
    List<Ground> getGoundListByFutsal(int futsal_id);
    boolean checkGroundAvailability(int futsal_id, int ground_id);
    Ground getGroundById(int futsal_id, int ground_id);

}
