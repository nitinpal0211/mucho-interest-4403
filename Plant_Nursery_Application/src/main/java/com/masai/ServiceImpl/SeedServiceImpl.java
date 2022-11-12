package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.SeedException;
import com.masai.Repository.SeedDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.SeedService;
import com.masai.model.CurrentUserSession;
import com.masai.model.Seed;

@Service
public class SeedServiceImpl implements SeedService {
	@Autowired
	private SeedDao sdao;
	@Autowired
	private SessionDao sessionDao;

	@Override
	public Seed addSeed(Seed seed,String key) throws SeedException, CustomerException {
    CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to add a seed.");
		}
		
		Seed p = sdao.findByCommonName(seed.getCommonName());
		if(p==null) {
			Seed saveSeed = sdao.save(seed);
			return saveSeed;
		}else {
			throw new SeedException("seed already exists...");
		}
	}

	@Override
	public Seed updateSeed(Seed seed,String key) throws SeedException, CustomerException {
		
		   CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new CustomerException("Please Enter a Valid Key to update a Seed...");
			}else {
				Optional<Seed> opt = sdao.findById(seed.getSeedId());
				if(opt.isPresent()) {
					Seed updateSeed=sdao.save(seed);
					return updateSeed;
				}else {
					throw new SeedException("Invalid Seed details...");
				}
			}
		
	}

	@Override
	public Seed deleteSeed(Integer seedId,String key) throws SeedException, CustomerException {
         CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to delete a Seed..");
		}else {
			 Optional<Seed> opt = sdao.findById(seedId);
			  if(opt.isPresent()) {
				  Seed s= opt.get();
				  sdao.delete(s);
				  return s;
			  }else {
				  throw new SeedException("No Seed found by id "+ seedId);
			  }
		}

	 
	}

	@Override
	public Seed viewSeed(Integer seedId) throws SeedException {
		return sdao.findById(seedId).orElseThrow(()-> new SeedException("No seed found with id "+ seedId));
	}

	@Override
	public Seed viewSeed(String name) throws SeedException {
       Seed seed = sdao.findByCommonName(name);
       if(seed==null) {
    	   throw new SeedException("No seed found with Common Name "+ name);
       }else {
    	   return seed;
       }
	}

	@Override
	public List<Seed> viewAllSeed() throws SeedException {
		List<Seed> seeds= sdao.findAll();
		if(seeds.size()>0) {
			return seeds;
		}else {
			throw new SeedException("No seeds found..");
		}
		
	}

	@Override
	public List<Seed> viewAllSeed(String typeOfSeed) throws SeedException {
		List<Seed> seeds = sdao.findAllBytypeOfSeeds(typeOfSeed);
		if(seeds.size()>0) {
			return seeds;
		}else {
			throw new SeedException("No seeds found..");
		}
	}

}
