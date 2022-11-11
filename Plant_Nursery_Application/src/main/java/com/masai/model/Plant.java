package com.masai.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ManyToAny;

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
	private Integer plantHeight;
	private String plantspread;
	@Column(unique = true)
	private String commonName;
	private String bloomTime;
	private String medicinalOrCulinaryUse;
	private String difficultyLevel;
	private String temparature;
	private String typeOfPlant;
	private String plantDescription;
	private Integer plantStock;
	private Double plantCost;
	
	@ManyToMany(cascade = CascadeType.ALL)

	private Set<Planter> planters;
	

}
