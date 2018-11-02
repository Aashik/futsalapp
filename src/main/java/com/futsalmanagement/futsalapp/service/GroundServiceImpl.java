package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.GroundDao;
import com.futsalmanagement.futsalapp.entity.Futsal;
import com.futsalmanagement.futsalapp.entity.Ground;
import com.futsalmanagement.futsalapp.service.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroundServiceImpl implements GroundService {

    @Autowired
    private GroundDao groundDao;

    @Autowired
    private FutsalService futsalService;

    @Override
    public Ground insert(Ground g) {
        Ground savedGround = groundDao.save(g);
        return (savedGround != null) ? savedGround : null;
    }

    @Override
    public List<Ground> getAll() {
        return groundDao.findAll();
    }

    @Override
    public Ground getByFutsalId(String futsal_id) {
        List<Ground> groundList = groundDao.findAll();
        for(Ground g : groundList){
            if (g.getFutsal().equals(futsal_id))
                return g;
        }
        return null;
    }




}
