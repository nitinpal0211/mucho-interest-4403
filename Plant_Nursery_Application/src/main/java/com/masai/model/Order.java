package com.masai.model;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;   
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	private LocalDate localDate;
	
	@NotNull(message="Transaction Mode Can't be null")
	private String TransactionMode;
	
	@Min(value=1,message="Quantity greater than 0")
	private Integer quantity;
	
	@Min(value = 1, message = "Cost should be greater than 1")
	private Integer totalCost;
	
	@OneToMany(cascade = CascadeType.ALL)
	private @Valid List<Planter> planters;
	
	@OneToMany(cascade = CascadeType.ALL)
	private @Valid List<Plant> plants;
	
	@OneToMany(cascade = CascadeType.ALL)
	private @Valid List<Seed> Seeds;
	
	@NotNull(message="userid can't be null")
	private Integer userid;

	
	 
}
