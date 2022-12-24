package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.SeedException;
import com.masai.model.Seed;

public interface SeedService {
	
	// this method will add a seed
	public Seed addSeed(Seed seed,String key) throws SeedException, LoginException;
	
	// this method will update a seed
	public Seed updateSeed(Seed seed,String key) throws SeedException, LoginException;
	
	// this method will delete a particular seed
	public Seed deleteSeed(Integer seedId,String Key) throws SeedException, LoginException;
	
	// this method will get a particular seed
	public Seed viewSeed(Integer seedId,String Key) throws SeedException,LoginException;
	
	// this method will get a particular seed
	public Seed viewSeed(String name,String Key) throws SeedException,LoginException;
	
	// this method will return all seeds
	public List<Seed> viewAllSeed(String Key) throws SeedException,LoginException;
	
	// this method will return particular seeds
	public List<Seed> viewAllSeed(String typeOfSeed,String Key) throws SeedException,LoginException;
	

}
