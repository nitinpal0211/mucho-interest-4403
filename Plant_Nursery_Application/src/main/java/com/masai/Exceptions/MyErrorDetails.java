package com.masai.Exceptions;

import java.time.LocalDateTime;

public class MyErrorDetails {

	
	private LocalDateTime timestap;
	private String message;
	private String Description;
	
	public MyErrorDetails() {
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTimestap() {
		return timestap;
	}

	public void setTimestap(LocalDateTime timestap) {
		this.timestap = timestap;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public MyErrorDetails(LocalDateTime timestap, String message, String description) {
		super();
		this.timestap = timestap;
		this.message = message;
		Description = description;
	}

	@Override
	public String toString() {
		return "MyErrorDetails [timestap=" + timestap + ", message=" + message + ", Description=" + Description + "]";
	}
	
}
