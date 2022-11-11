package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	@NotNull
	@Min(value = 1,message = "Minimum value is 1 and negative value not allowed.")
	private Integer houseNo;
	@Size(min=3,max=20,message="please enter your colony")
	private String colony;
	@Size(min=3,max=20,message="please enter your city")
	private String city;
	@Size(min=3,max=20,message="please enter your state")
	private String state;
	@NotNull
	@Min(value =100,message = "Minimum value is 100 and negative value not allowed.")
	private Integer pincode;
}
