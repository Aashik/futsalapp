package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.GroundDao;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroundServiceImpl implements GroundService {

    @Autowired
    private GroundDao groundDao;

    @Override
    public Ground insert(Ground g) {
        Ground savedGround = groundDao.save(g);
        return (savedGround != null) ? savedGround : null;
    }

    @Override
    public List<Ground> getGoundListByFutsal(int futsal_id) {
        List<Ground> groundlist = groundDao.findAll();
        List<Ground> groundlistbyFutsal = groundlist.stream()
                                          .filter(ground -> ground.getFutsal().getFutsal_id() == futsal_id)
                                          .collect(Collectors.toList());
        return groundlistbyFutsal;

    }

    @Override
    public boolean checkGroundAvailability(int futsal_id, int ground_id) {
        List<Ground> groundListofAfutsal = groundDao.findAll().stream()
                .filter(ground -> ground.getFutsal().getFutsal_id() == futsal_id).collect(Collectors.toList());
        //status later change to AVAILABLE
        Ground foundGround = groundListofAfutsal.stream().filter(ground -> ground.getGround_id() == ground_id && ground.getStatus().equals("ACTIVE"))
                .findAny().orElse(null);
        return foundGround != null ? true : false;
    }


    @Override
    public Ground getGroundById(int futsal_id, int ground_id) {
        List<Ground> groundListofAfutsal = groundDao.findAll().stream()
                .filter(ground -> ground.getFutsal().getFutsal_id() == futsal_id).collect(Collectors.toList());
        Ground foundGround = groundListofAfutsal.stream()
                .filter(ground -> ground.getGround_id() == ground_id).findAny().orElse(null);
        return foundGround;
    }
}
