package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.model.Customer;

public interface AdminService {

	// this method will add a customer while taking customer details and key as a parameter 
	public Customer addCustomer(Customer customer,String key) throws CustomerException;
	
	// this method will update a customer while taking customer details and key as a parameter 
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	
	// this method will delete a customer while taking customer email and key as a parameter 
	public Customer deleteCustomer(String email,String Key) throws CustomerException;
	
	// this method will get a customer while taking customer id and key as a parameter 
	public Customer viewCustomer(Integer customerId,String key) throws CustomerException;
	
	// this method will show all customers while taking key as a parameter
	public List<Customer> viewAllCustomers(String key) throws CustomerException;
	
	// this method will check validation of customer while taking userName,password and key as a parameter
	public Customer validateCustomer(String userName,String password,String key) throws CustomerException;
}
