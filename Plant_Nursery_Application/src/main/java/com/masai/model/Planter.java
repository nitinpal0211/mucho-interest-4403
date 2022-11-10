package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
	
	@OneToMany(cascade= CascadeType.ALL)
	Seed Seeds;
	
	@OneToMany(cascade= CascadeType.ALL)
	Plant Plants;

	public Integer getPlanterId() {
		// TODO Auto-generated method stub
		this.planterId = planterId;
		return planterId;
	}

}


