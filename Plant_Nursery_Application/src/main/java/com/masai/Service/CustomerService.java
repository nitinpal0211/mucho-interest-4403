package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;



public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	public Customer deleteCustomer(String email,String Key) throws CustomerException;
	public List<Plant> viewAllPlants(String Key) throws CustomerException;
	public List<Planter> viewAllPlanters(String Key) throws CustomerException;
	public List<Seed> viewAllSeeds(String Key) throws CustomerException;
}