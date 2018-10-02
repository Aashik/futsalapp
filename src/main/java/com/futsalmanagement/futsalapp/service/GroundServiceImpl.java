package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.GroundDao;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroundServiceImpl implements GroundService {

    @Autowired
    private GroundDao groundDao;

    @Override
    public Ground insert(Ground g) {
        Ground savedGround = groundDao.save(g);
        return (savedGround != null) ? savedGround : null;
    }
}
