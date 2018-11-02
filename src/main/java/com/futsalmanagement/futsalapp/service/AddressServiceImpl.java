package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.AddressDao;
import com.futsalmanagement.futsalapp.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public Address insert(Address a) {
        Address insertedaddress = addressDao.save(a);
        return insertedaddress;
    }
}
