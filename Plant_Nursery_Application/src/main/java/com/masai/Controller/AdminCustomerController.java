package com.masai.Controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.CustomerException;
import com.masai.Service.CustomerService;
import com.masai.model.Customer;

@RestController
public class AdminCustomerController {

	@Autowired
	private CustomerService customerService;
	
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
	
}
