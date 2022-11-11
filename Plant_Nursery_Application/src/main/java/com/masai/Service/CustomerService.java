package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.model.Customer;



public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	public Customer deleteCustomer(Customer tenant,String Key) throws CustomerException;
	public Customer viewCustomer(int customerId) throws CustomerException;
	public List<Customer> viewAllCustomers() throws CustomerException;
	public Customer validateCustomer(String userName,String password) throws CustomerException;
}