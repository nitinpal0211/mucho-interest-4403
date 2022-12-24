package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.LoginException;
import com.masai.Service.LoginService;
import com.masai.model.CustomerDTO;

@RestController
public class CustomerLoginController {

	@Autowired
	private LoginService lService;
	
	// this method will return response entity of String
	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody CustomerDTO dto) throws LoginException
	{
		String message = lService.logIntoAccount(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	// this method will return response entity of String
	@GetMapping("/logout/{key}")
	public ResponseEntity<String> logoutCustomer(@PathVariable("key") String key) throws LoginException
	{
		String message = lService.logOutFromAccount(key);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
}
