package com.masai.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seed {

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
	
	@ManyToMany()
	private Set<Planter> seedPlanters;
}
