package com.emtech.logistics.service;

import com.emtech.logistics.dto.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    boolean deleteCustomer(Long id);
}
