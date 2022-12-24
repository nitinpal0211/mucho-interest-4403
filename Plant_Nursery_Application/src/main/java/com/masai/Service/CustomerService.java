package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;



public interface CustomerService {

	// this method will add a customer 
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	// this method will update a customer
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	
	// this method will delete a customer
	public Customer deleteCustomer(String email,String Key) throws CustomerException;
	
	// this method will return all plants 
	public List<Plant> viewAllPlants(String Key) throws CustomerException;
	
	// this method will return all planters
	public List<Planter> viewAllPlanters(String Key) throws CustomerException;
	
	//this method will return all seeds
	public List<Seed> viewAllSeeds(String Key) throws CustomerException;
}