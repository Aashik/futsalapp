package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.FutsalDao;
import com.futsalmanagement.futsalapp.entity.Address;
import com.futsalmanagement.futsalapp.entity.Futsal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public Futsal getFutsalById(int futsal_id) {
        Futsal foundFutsal = futsalDao.findAll().stream()
                .filter(futsal -> futsal.getFutsal_id() == futsal_id).findAny().orElse(null);
        return foundFutsal;
    }

    @Override
    public boolean checkFutsalAvailability(int futsal_id) {
        Futsal foundFutsal = futsalDao.findAll().stream().filter(futsal -> futsal_id == futsal.getFutsal_id()).findAny().orElse(null);
        return (foundFutsal != null) ? true : false;
    }

    @Override
    public List<String> getAllFutsalName() {
        List<String> futsalNameList = futsalDao.findAll().stream().map(futsal -> futsal.getFutsal_name()).collect(Collectors.toList());
        return futsalNameList;
    }

    //    @Override
//    public Futsal getFutsalByCode(String futsal_code) {
//        List<Futsal> futsallist = futsalDao.findAll();
//        Futsal foundfutsal = futsallist.stream()
//                .filter(futsal -> futsal_code.equals(futsal.getFutsal_code())).findAny().orElse(null);
//        return foundfutsal;
//    }


}
