package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.SeedException;
import com.masai.model.Seed;

public interface SeedService {
	
	public Seed addSeed(Seed seed,String key) throws SeedException, LoginException;
	public Seed updateSeed(Seed seed,String key) throws SeedException, LoginException;
	public Seed deleteSeed(Integer seedId,String Key) throws SeedException, LoginException;
	public Seed viewSeed(Integer seedId,String Key) throws SeedException,LoginException;
	public Seed viewSeed(String name,String Key) throws SeedException,LoginException;
	public List<Seed> viewAllSeed(String Key) throws SeedException,LoginException;
	public List<Seed> viewAllSeed(String typeOfSeed,String Key) throws SeedException,LoginException;
	

}
