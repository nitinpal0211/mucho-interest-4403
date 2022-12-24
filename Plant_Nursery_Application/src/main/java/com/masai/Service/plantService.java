package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlantException;
import com.masai.model.Plant;


public interface plantService {
	
	// this method will add a plant
	public Plant addPlant(Plant plant,String key) throws PlantException,LoginException; 

	// this method will update a plant
	public Plant updatePlant(Plant plant,String key) throws PlantException,LoginException;
	
	// this method will delete a plant
	public Plant deletePlant(Integer plantId,String key) throws PlantException,LoginException;
	
	// this method will return plant 
	public Plant viewPlantById(Integer plantId,String key) throws PlantException,LoginException;
	
	// this method will return plant
	public Plant viewPlantByCommonName(String name,String key) throws PlantException,LoginException;
	
	// this method will return all plants
	public List<Plant> viewAllPlant(String key) throws PlantException,LoginException;
	
	// this method will return particular plants
	public List<Plant> viewAllPlantByTypeOfPlant(String type,String key) throws PlantException,LoginException;
	
	
}
