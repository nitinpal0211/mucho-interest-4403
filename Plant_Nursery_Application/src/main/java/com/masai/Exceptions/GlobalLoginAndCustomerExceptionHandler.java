package com.masai.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalLoginAndCustomerExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(LoginException le,WebRequest req)
	{
		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(le.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(CustomerException ce,WebRequest req)
	{
		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(OrderException ce,WebRequest req)
	{
		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(MethodArgumentNotValidException ve)
	{
		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ve.getMessage());
		error.setDescription(ve.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<LoginAndCustomerErrorDetails> myExceptionHandler(Exception ce,WebRequest req)
	{
		LoginAndCustomerErrorDetails error = new LoginAndCustomerErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ce.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<LoginAndCustomerErrorDetails>(error , HttpStatus.BAD_REQUEST);
	}
	
}
