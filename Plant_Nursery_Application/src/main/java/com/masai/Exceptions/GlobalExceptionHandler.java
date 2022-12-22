package com.masai.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
  
	@ExceptionHandler(PlantException.class)
	public ResponseEntity<MyErrorDetails> plantExceptionHandler(PlantException p, WebRequest req ){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(p.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestap(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDescription(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(SeedException.class)
	public ResponseEntity<MyErrorDetails> seedExceptionHandler(SeedException p, WebRequest req ){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(p.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> illegalArgumnetExceptionHandler(IllegalArgumentException ie, WebRequest req ){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(ie.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ne, WebRequest req ){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(ne.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<MyErrorDetails> allExceptionHandler(Exception e,WebRequest req){
		MyErrorDetails err = new MyErrorDetails();
		err.setTimestap(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
}
