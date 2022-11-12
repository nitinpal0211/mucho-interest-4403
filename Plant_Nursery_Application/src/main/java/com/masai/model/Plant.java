package com.masai.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	private Integer plaintId;
	
	@Min(value = 1,message= " Plant height should be more then 0")
	private Integer plantHeight;
	@NotBlank(message = "Enter plant Spread")
	private String plantspread;
	@Column(unique = true)
	@NotBlank(message = "Enter plant Common Name")
	private String commonName;
	@NotBlank(message = "Enter plant Bloom Time")
	private String bloomTime;
	@NotBlank(message = "Enter plant use")
	private String medicinalOrCulinaryUse;
	@NotBlank(message = "Enter plant Difficulty Level")
	private String difficultyLevel;
	@NotBlank(message = "Enter plant Temparture")
	private String temparature;
	@NotBlank(message = "Enter type of Plant")
	private String typeOfPlant;
	@NotBlank(message = "Enter plant Description")
	private String plantDescription;
	@Min(value=1 , message = "Plant Stock should be more than 0")
	private Integer plantStock;
	@Min(value=1 , message = "Plant cost should be more than 0.0")
	private Double plantCost;

	
     @JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Planter planter;
	
	

	

}
