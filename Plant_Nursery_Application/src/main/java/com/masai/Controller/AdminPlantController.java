package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlantException;
import com.masai.Repository.PlantDao;
import com.masai.Service.plantService;
import com.masai.model.Plant;

@RestController
public class AdminPlantController {

	@Autowired
	private plantService pService;;
	
	@PostMapping("/plant/{key}")
	public ResponseEntity<Plant> addNewPlantHandler(@RequestBody Plant plant,@PathVariable("key") String key) throws PlantException,LoginException{
		
		Plant savePlant = pService.addPlant(plant,key);
		return new ResponseEntity<Plant>(savePlant,HttpStatus.CREATED);
		
		
	}
	@PutMapping("/plant/{key}")
	public ResponseEntity<Plant> updatePlantHandler(@RequestBody Plant palnt,@PathVariable("key") String key) throws PlantException, LoginException{
		
		Plant updatedPlant = pService.updatePlant(palnt,key);
		return new ResponseEntity<Plant>(updatedPlant,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/plant/{id}/{key}")
	public ResponseEntity<Plant> deletePlantByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws PlantException, LoginException{
	  Plant plant=	pService.deletePlant(id,key);
	  return new ResponseEntity<Plant>(plant,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getPlantById/{id}/{key}")
	public ResponseEntity<Plant> viewPlantByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws PlantException, LoginException{
		Plant plant = pService.viewPlantById(id,key);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
	}
	
	@GetMapping("/getPlantByName/{name}/{key}")
	public ResponseEntity<Plant> viewPlantByCommonNameHandler(@PathVariable("name") String name,@PathVariable("key") String key) throws PlantException, LoginException{
		Plant plant = pService.viewPlantByCommonName(name,key);
		return new ResponseEntity<Plant>(plant,HttpStatus.OK);
	}
	
	
	@GetMapping("/allPlants/{key}")
	public ResponseEntity<List<Plant>> viewAllPlantsHandler(@PathVariable("key") String key) throws PlantException, LoginException{
		List<Plant> plist = pService.viewAllPlant(key);
		return new ResponseEntity<List<Plant>>(plist,HttpStatus.OK);
	}
	
	
	@GetMapping("/allPlantByType/{type}/{key}")
	public ResponseEntity<List<Plant>> viewAllPlantsByTypeOfPlant(@PathVariable("type") String type,@PathVariable("key") String key) throws PlantException, LoginException{
		List<Plant> plist = pService.viewAllPlantByTypeOfPlant(type,key);
		return new ResponseEntity<List<Plant>>(plist,HttpStatus.OK);
	}
}
