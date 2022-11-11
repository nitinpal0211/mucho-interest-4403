package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.model.Customer;

public interface AdminService {

	public Customer addCustomer(Customer customer,String key) throws CustomerException;
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	public Customer deleteCustomer(Customer tenant,String Key) throws CustomerException;
	public Customer viewCustomer(Integer customerId,String key) throws CustomerException;
	public List<Customer> viewAllCustomers(String key) throws CustomerException;
	public Customer validateCustomer(String userName,String password,String key) throws CustomerException;
}
