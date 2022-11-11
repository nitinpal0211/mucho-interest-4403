package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.SeedException;
import com.masai.model.Seed;

public interface SeedService {
	
	public Seed addSeed(Seed seed) throws SeedException;
	public Seed updateSeed(Seed seed,String key) throws SeedException, CustomerException;
	public Seed deleteSeed(Integer seedId,String Key) throws SeedException, CustomerException;
	public Seed viewSeed(Integer seedId) throws SeedException;
	public Seed viewSeed(String name) throws SeedException;
	public List<Seed> viewAllSeed() throws SeedException;
	public List<Seed> viewAllSeed(String typeOfSeed) throws SeedException;
	

}
