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
import com.masai.Exceptions.SeedException;
import com.masai.Service.SeedService;
import com.masai.model.Seed;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class SeedController {
	@Autowired
	private SeedService sService;
	
	@PostMapping("/seed/{key}")
	public ResponseEntity<Seed> addSeedHandler(@RequestBody Seed seed,@PathVariable("key")String key) throws SeedException,CustomerException{
		
		Seed s = sService.addSeed(seed,key);
		return new ResponseEntity<Seed>(s,HttpStatus.CREATED);
	}
	
	@PutMapping("/seed/{key}")
	public ResponseEntity<Seed> updateSeedHandler(@RequestBody Seed seed,@PathVariable("key") String key) throws SeedException, CustomerException{
		Seed s = sService.updateSeed(seed, key);
		return new ResponseEntity<Seed>(s,HttpStatus.ACCEPTED);
	}
	
	

	@DeleteMapping("/seed/{id}/{key}")
	public ResponseEntity<Seed> deleteSeedByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws SeedException, CustomerException{
		Seed seed = sService.deleteSeed(id, key);
		return new ResponseEntity<Seed>(seed,HttpStatus.OK);
	}
	
	@GetMapping("/viewSeedById/{id}")
	public ResponseEntity<Seed> viewSeedById(@PathVariable("id") Integer id) throws SeedException{
		Seed seeds = sService.viewSeed(id);
		return new ResponseEntity<Seed>(seeds,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewSeedByType/{type}")
	public ResponseEntity<List<Seed>> viewAllSeedByType(@PathVariable("type") String type) throws SeedException{
		
		List<Seed> seeds = sService.viewAllSeed(type);
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.ACCEPTED);
	}


}
