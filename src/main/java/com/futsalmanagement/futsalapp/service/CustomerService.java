package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Customer;

import javax.validation.constraints.Max;
import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer);
    List<Customer> getAllCustomerByFutsal(int futsal_id);
    Customer ifPresentCustomer(String contact_num, int futsal_id);

}
