package com.masai.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDTO {

	@Email(message = "please enter a valid email for placing the order")
	private String customerEmail;
	@Min(value = 0,message = "planterId cannot be negative,but if do not want to buy then put id 0")
	private Integer planterId;
	@Min(value = 0,message = "plantId cannot be negative,but if do not want to buy then put id 0")
	private Integer plantId;
	@Min(value = 0,message = "SeedId cannot be negative,but if do not want to buy then put id 0")
	private Integer SeedId;
	
	@Min(value = 1,message = "product quantity at least 1 for placing the order")
	private Integer quantity;
	
	@Size(min=3,max=20,message = "please enter transaction Mode UPI or Wallet or Net Banking")
	private String transactionMode;
	
}
