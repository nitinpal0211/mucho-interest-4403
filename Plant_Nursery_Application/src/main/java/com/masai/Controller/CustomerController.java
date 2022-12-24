package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.PlantException;
import com.masai.Exceptions.PlanterException;
import com.masai.Exceptions.SeedException;
import com.masai.Service.CustomerService;
import com.masai.Service.PlanterService;
import com.masai.Service.SeedService;
import com.masai.Service.plantService;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private plantService plantService;
	
	@Autowired
	private PlanterService planterService;
	
	@Autowired
	private SeedService seedService;
	
	// this method will return response entity of Customer
	@PostMapping("/customers")
	public ResponseEntity<Customer> CreateNewAccount(@Valid @RequestBody Customer customer) throws CustomerException
	{
		System.out.println("customer  : "+customer);
		Customer user = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(user,HttpStatus.CREATED);
	}
	
	// this method will return response entity of Customer
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateAccount(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.upadateCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.ACCEPTED);
	}
	
	
	// this method will return response entity of Customer
	@DeleteMapping("/customers/{email}/{key}")
	public ResponseEntity<Customer> deleteAccount(@PathVariable("email") String email,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.deleteCustomer(email,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.OK);
	}
	
	// this method will return response entity of list of plant
	@GetMapping("/customers/viewAllPlants/{key}")
	public ResponseEntity<List<Plant>> viewAllPlants(@PathVariable("key") String key) throws CustomerException, PlantException
	{
		List<Plant> list = customerService.viewAllPlants(key);
		
		return new ResponseEntity<List<Plant>>(list,HttpStatus.OK);
	}
	
	// this method will return response entity of list of planters
	@GetMapping("/customers/viewAllPlanters/{key}")
	public ResponseEntity<List<Planter>> viewAllPlanters(@PathVariable("key") String key) throws CustomerException, PlanterException
	{
		List<Planter> list = customerService.viewAllPlanters(key);
		
		return new ResponseEntity<List<Planter>>(list,HttpStatus.OK);
	}
	
	// this method will return response entity of list of seed
	@GetMapping("/customers/viewAllSeeds/{key}")
	public ResponseEntity<List<Seed>> viewAllSeeds(@PathVariable("key") String key) throws CustomerException, SeedException
	{
		List<Seed> list = customerService.viewAllSeeds(key);
		
		return new ResponseEntity<List<Seed>>(list,HttpStatus.OK);
	}
	
}
