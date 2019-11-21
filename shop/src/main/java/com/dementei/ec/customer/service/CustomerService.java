package com.dementei.ec.customer.service;

import com.dementei.ec.customer.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);

    Customer getCustomerById(String email);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Customer update);

    void deleteCustomerById(String email);
}
