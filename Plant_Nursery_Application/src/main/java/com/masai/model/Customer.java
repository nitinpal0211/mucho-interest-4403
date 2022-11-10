package com.masai.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Size(min=3,max=20,message="please enter your name")
	private String customerName;
	@Email(message = "please enter a valid email")
	private String customerEmail;
	@Size(min=3,max=20,message="please enter your username")
	private String customerUserName;
	@Size(min=3,max=8,message="please enter password minimum character 3 and maximum character is 8.")
	private String customerPassword;
	
	@OneToMany
	private Set<Address> addresses=new HashSet<>();
}
