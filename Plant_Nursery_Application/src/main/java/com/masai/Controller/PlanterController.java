package com.masai.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlanterException;
import com.masai.model.Planter;
import com.masai.Service.PlanterService;

@RestController
@RequestMapping("/Planter")
public class PlanterController {

	@Autowired
	private PlanterService planterService;
	
	
	@PostMapping("/add/{key}")
	public ResponseEntity<Planter> addNewPlanter(@Valid @RequestBody Planter planter,@PathVariable("key") String key) throws PlanterException, LoginException{
		
		Planter addPlanter = null;
		
		addPlanter = planterService.addPlanter(planter,key);

		return new ResponseEntity<Planter>(addPlanter, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{planterId}/{key}")
	public ResponseEntity<Planter> deletePlanter(@PathVariable Integer planterId,@PathVariable("key") String key )throws PlanterException, LoginException{

		Planter deletedPlanter = null;
		
		deletedPlanter = planterService.deletePlanter(planterId,key);
		
		return new ResponseEntity<Planter>(deletedPlanter, HttpStatus.OK);
	}
	
	@PutMapping("/update/{key}")
	public ResponseEntity<Planter> updatePlanter(@RequestBody Planter planter,@PathVariable("key") String key) throws  PlanterException, LoginException{

		Planter updatedPlanter = null;
		updatedPlanter = planterService.updatePlanter(planter,key);

		return new ResponseEntity<Planter>(updatedPlanter, HttpStatus.CREATED);
	}
	
	@GetMapping("/byID/{planterId}/{key}")
	public ResponseEntity<Planter> getPlanterById(@PathVariable Integer planterId,@PathVariable("key") String key)throws PlanterException, LoginException{
		
		Planter specificPlanter = planterService.viewPlanter(planterId,key);

		return new ResponseEntity<Planter>(specificPlanter, HttpStatus.OK);
	}
	
	@GetMapping("/{planterShape}/{key}")
	public ResponseEntity<List<Planter>> viewPlanterByShape(@PathVariable String planterShape,@PathVariable("key") String key) throws PlanterException, LoginException{

		List<Planter> plantersByShape = planterService.viewPlanter(planterShape,key);

		return new ResponseEntity<List<Planter>>(plantersByShape, HttpStatus.OK);
	}
	@GetMapping("/all/{key}")
	public ResponseEntity<List<Planter>> viewAllPlanters(@PathVariable("key") String key) throws PlanterException, LoginException{

		List<Planter> allPlanters = planterService.viewAllPlanters(key);
		
		return new ResponseEntity<List<Planter>>(allPlanters, HttpStatus.OK);
	}
	

	@GetMapping("/all/range/{key}")
	public ResponseEntity<List<Planter>> viewAllPlantersByTypeOfPlanter(@RequestParam Double minCost,@RequestParam Double maxCost,@PathVariable("key") String key) throws PlanterException, LoginException{
		   
		List<Planter> allPlanters = planterService.viewAllPlanters(minCost, maxCost,key);	


		return new ResponseEntity<List<Planter>>(allPlanters, HttpStatus.OK);
	}
	
}
