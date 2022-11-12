package com.masai.model;

import java.util.List;
import java.util.Set;
import java.time.LocalDateTime;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Planter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Integer planterId;
	
	@Min(value=1 , message = "Planter height should be more than 0")
	private Double planterHeight;
	
	@Min(value=1 , message = "Planter capacity cannot be 0")
	private Double planterCapacity;
	
	@Min(value=1 , message = "Drainage holes should not be 0")
	private Integer drainageHoles;
	
	@NotBlank
	private String planterColor;
	
	@NotBlank
	private String planterShape;
	
	@Min(value=0 , message = "Planter stock should be atleast 0")
	private Integer planterStock;
	
	@Min(value=1 , message = "Planter cost cannot be 0")
	private Integer planterCost;
	

	
	@OneToMany(cascade= CascadeType.ALL,mappedBy = "seedPlanter")
	 private List<Seed> seeds;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "planter")
	private List<Plant> Plants;


	
//	@ManyToOne(cascade = CascadeType.ALL)
//    private Orders placeorder;



}


