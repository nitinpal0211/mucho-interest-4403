package com.masai.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	@Email
	private String customerEmail;
	@Size(min=3,max=8,message="please enter password minimum character 3 and maximum character is 8.")
	private String customerPassword;
	
}
