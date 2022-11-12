package com.masai.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seedId;
	
	private String commonName;
	private String bloomTime;
	private String watering;
	private String difficultyLevel;
	private String temparture;
	private String typeOfSeeds;
	private String seedsDescription;
	private Integer seedsStock;
	private Double seedsCost;
	private Integer seedsPerPacket;
	
//	@JsonIgnore
//	@ManyToMany(cascade = CascadeType.ALL)
//	private Set<Planter> seedPlanters;
}
