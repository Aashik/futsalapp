package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.entity.Ground;

import java.util.List;

public interface GroundService  {
    Ground insert(Ground g);
    List<Ground> getAll();
    Ground getByFutsalId(String futsal_id);
}
