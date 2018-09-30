package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.FutsalDao;
import com.futsalmanagement.futsalapp.entity.Futsal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutsalServiceImpl implements FutsalService {

    @Autowired
    private FutsalDao futsalDao;

    @Override
    public Futsal insert(Futsal f) {
        Futsal savedfutsal = futsalDao.save(f);
        return (savedfutsal != null && savedfutsal.getMobile_no().equals(f.getMobile_no())) ? savedfutsal : null;
    }

    @Override
    public boolean checkFutsalDuplication(String futsal_code) {
        List<Futsal> futsallist = futsalDao.findAll();
        Futsal foundFutsal = futsallist.stream()
                .filter(futsal -> futsal_code.equals(futsal.getFutsal_code())).findAny().orElse(null);
        return (foundFutsal != null)? true:false;

    }
}
