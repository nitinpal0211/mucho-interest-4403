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
	@NotBlank(message = "Enter Seed Common Name")
	@Column(unique = true)
	private String commonName;
	@NotBlank(message = "Enter Seed Bloom Time..")
	private String bloomTime;
	@NotBlank(message = "Enter Seed watering")
	private String watering;
	@NotBlank(message = "Enter Seed Difficulty level")
	private String difficultyLevel;
	@NotBlank(message = "Enter Seed Temp. ")
	private String temparture;
	@NotBlank(message = "Enter Type of seed ")
	private String typeOfSeeds;
	@NotBlank(message = "Enter seed Description ")
	private String seedsDescription;
	@Min(value = 1,message = "seed stock value should be more then 0")
	private Integer seedsStock;
	@Min(value = 1,message = "seed cost value should be more then 0.0")
	private Double seedsCost;
	@Min(value = 1,message = "seed/packet value should be more then 0")
	private Integer seedsPerPacket;
	
     	@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
     private Seed seedPlanter;
}
