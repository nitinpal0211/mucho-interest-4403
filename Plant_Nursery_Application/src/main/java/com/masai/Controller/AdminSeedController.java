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

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.SeedException;
import com.masai.Service.SeedService;
import com.masai.model.Seed;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class AdminSeedController {
	@Autowired
	private SeedService sService;
	
	// this method will return response entity of Seed
	@PostMapping("/seed/{key}")
	public ResponseEntity<Seed> addSeedHandler(@RequestBody Seed seed,@PathVariable("key")String key) throws SeedException,CustomerException, LoginException{
		
		Seed s = sService.addSeed(seed,key);
		return new ResponseEntity<Seed>(s,HttpStatus.CREATED);
	}
	
	// this method will return response entity of Seed
	@PutMapping("/seed/{key}")
	public ResponseEntity<Seed> updateSeedHandler(@RequestBody Seed seed,@PathVariable("key") String key) throws SeedException, CustomerException, LoginException{
		Seed s = sService.updateSeed(seed, key);
		return new ResponseEntity<Seed>(s,HttpStatus.ACCEPTED);
	}
	
	
	// this method will return response entity of Seed
	@DeleteMapping("/seed/{id}/{key}")
	public ResponseEntity<Seed> deleteSeedByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws SeedException, CustomerException, LoginException{
		Seed seed = sService.deleteSeed(id, key);
		return new ResponseEntity<Seed>(seed,HttpStatus.OK);
	}
	
	// this method will return response entity of Seed
	@GetMapping("/viewSeedById/{id}/{key}")
	public ResponseEntity<Seed> viewSeedById(@PathVariable("id") Integer id,@PathVariable("key") String key) throws SeedException, LoginException{
		Seed seeds = sService.viewSeed(id,key);
		return new ResponseEntity<Seed>(seeds,HttpStatus.ACCEPTED);
	}
	
	// this method will return response entity of list of Seed
	@GetMapping("/viewSeedByType/{type}/{key}")
	public ResponseEntity<List<Seed>> viewAllSeedByType(@PathVariable("type") String type,@PathVariable("key") String key) throws SeedException, LoginException{
		
		List<Seed> seeds = sService.viewAllSeed(type,key);
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.ACCEPTED);
	}


}
