package com.futsalmanagement.futsalapp.service;


import com.futsalmanagement.futsalapp.dao.CustomerDao;
import com.futsalmanagement.futsalapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public List<Customer> getAllCustomerByFutsal(int futsal_id) {
        return null;
    }

    @Override
    public Customer ifPresentCustomer(String contact_num, int futsal_id) {
        return customerDao.findAll().stream()
                .filter(customer -> customer.getContact_number().equals(contact_num)
                        && customer.getFutsal().getFutsal_id() == futsal_id).findAny().orElse(null);
    }
}
