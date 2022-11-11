package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@NotNull(message="Please fill your User Name")
	@Size(min=3,max=20,message = "Your username should have minimum 3 character and maximum 20 character.")
	private String adminUserName;
	@NotNull(message="Please enter your password")
	@Size(min=3,max=8,message = "Your password should have minimum 3 character and maximum 8 character.")
	private String adminPassword;
}
