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
public class AdminCustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private plantService plantService;
	
	@Autowired
	private PlanterService planterService;
	
//	@Autowired
//	private SeedService seedService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) throws CustomerException
	{
		System.out.println("customer  : "+customer);
		Customer user = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.upadateCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customers/{key}")
	public ResponseEntity<Customer> deleteCustomer(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.deleteCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.OK);
	}
	
	@GetMapping("/customers/viewAllPlants/{key}")
	public ResponseEntity<List<Plant>> viewAllPlants(@PathVariable("key") String key) throws CustomerException, PlantException
	{
		List<Plant> list = plantService.viewAllPlant();
		
		return new ResponseEntity<List<Plant>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/customers/viewAllPlanters/{key}")
	public ResponseEntity<List<Planter>> viewAllPlanters(@PathVariable("key") String key) throws CustomerException, PlanterException
	{
		List<Planter> list = planterService.viewAllPlanters();
		
		return new ResponseEntity<List<Planter>>(list,HttpStatus.OK);
	}
	
//	@GetMapping("/customers/viewAllSeeds/{key}")
//	public ResponseEntity<List<Seed>> viewAllSeeds(@PathVariable("key") String key) throws CustomerException, SeedException
//	{
//		List<Seed> list = seedService.viewAllSeed();
//		
//		return new ResponseEntity<List<Seed>>(list,HttpStatus.OK);
//	}
	
}
