 package com.masai.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@NotNull(message = "please enter order date ")
	private LocalDate localDate;
	@Size(min = 3,max=20,message = "please enter transaction Mode UPI or Wallet or Net Banking")
	private String TransactionMode;
	@Min(value = 1,message = "order quantity should not be 0 ")
	private Integer quantity;
	@Min(value = 1,message = "order cost should not be 0")
	private Double totalCost;
	@NotNull(message = "please enter customer id")
	private Integer customerId;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Planter> orderPlanters;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Plant> orderPlants;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Seed> orderSeeds;
	
	
}
