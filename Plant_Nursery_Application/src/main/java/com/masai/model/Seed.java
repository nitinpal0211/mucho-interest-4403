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
	@Column(unique = true)
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
	
    @ManyToOne(cascade = CascadeType.ALL)
     private Seed seedPlanter;
}
