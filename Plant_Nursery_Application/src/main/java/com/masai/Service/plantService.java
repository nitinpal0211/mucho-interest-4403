package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlantException;
import com.masai.model.Plant;


public interface plantService {
	
	public Plant addPlant(Plant plant,String key) throws PlantException,LoginException; 

	public Plant updatePlant(Plant plant,String key) throws PlantException,LoginException;
	public Plant deletePlant(Integer plantId,String key) throws PlantException,LoginException;
	public Plant viewPlantById(Integer plantId,String key) throws PlantException,LoginException;
	public Plant viewPlantByCommonName(String name,String key) throws PlantException,LoginException;
	public List<Plant> viewAllPlant(String key) throws PlantException,LoginException;
	public List<Plant> viewAllPlantByTypeOfPlant(String type,String key) throws PlantException,LoginException;
	
	
}
