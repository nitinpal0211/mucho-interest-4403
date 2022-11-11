package com.masai.Exceptions;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndCustomerErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String description;
	
}
