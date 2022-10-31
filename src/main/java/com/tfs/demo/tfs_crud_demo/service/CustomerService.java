package com.tfs.demo.tfs_crud_demo.service;

import com.tfs.demo.tfs_crud_demo.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(String customerId);

    public void saveCustomer(Customer theCustomer);

    public void disableCustomer(String customerId);

    public boolean checkDuplicateCustomerId(String customerId);

}
