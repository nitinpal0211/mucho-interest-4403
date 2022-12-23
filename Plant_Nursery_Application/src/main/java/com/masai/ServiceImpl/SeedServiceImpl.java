package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.SeedException;
import com.masai.Repository.AdminSessionDao;
import com.masai.Repository.SeedDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.SeedService;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Seed;

@Service
public class SeedServiceImpl implements SeedService {
	@Autowired
	private SeedDao sdao;
	@Autowired
	private SessionDao sessionDao;
	@Autowired
	private AdminSessionDao adminsessioDao;
	
	// this method will add a seed
	@Override
	public Seed addSeed(Seed seed,String key) throws SeedException, LoginException {
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		
		Seed p = sdao.findByCommonName(seed.getCommonName());
		if(p==null) {
			Seed saveSeed = sdao.save(seed);
			return saveSeed;
		}else {
			throw new SeedException("seed already exists...");
		}
	}

	// this method will update a seed
	@Override
	public Seed updateSeed(Seed seed,String key) throws SeedException, LoginException {		
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
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

	// this method will delete a particular seed
	@Override
	public Seed deleteSeed(Integer seedId,String key) throws SeedException, LoginException {
         CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		
			 Optional<Seed> opt = sdao.findById(seedId);
			  if(opt.isPresent()) {
				  Seed s= opt.get();
				  sdao.delete(s);
				  return s;
			  }else {
				  throw new SeedException("No Seed found by id "+ seedId);
			  }
		

	 
	}

	// this method will return a particular seed
	@Override
	public Seed viewSeed(Integer seedId,String key) throws SeedException, LoginException {
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		return sdao.findById(seedId).orElseThrow(()-> new SeedException("No seed found with id "+ seedId));
	}

	// this method will return a particular seed
	@Override
	public Seed viewSeed(String name,String key) throws SeedException, LoginException {
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
       Seed seed = sdao.findByCommonName(name);
       if(seed==null) {
    	   throw new SeedException("No seed found with Common Name "+ name);
       }else {
    	   return seed;
       }
	}

	// this will return all seeds
	@Override
	public List<Seed> viewAllSeed(String key) throws SeedException,LoginException {
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
         
		List<Seed> seeds= sdao.findAll();
		if(seeds.size()>0) {
			return seeds;
		}else {
			throw new SeedException("No seeds found..");
		}
		
	}

	// this method will return a particular seeds
	@Override
	public List<Seed> viewAllSeed(String typeOfSeed,String key) throws SeedException, LoginException {
CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		List<Seed> seeds = sdao.findAllBytypeOfSeeds(typeOfSeed);
		if(seeds.size()>0) {
			return seeds;
		}else {
			throw new SeedException("No seeds found..");
		}
	}

}
