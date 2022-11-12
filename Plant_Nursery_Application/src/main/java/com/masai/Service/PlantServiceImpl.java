package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlantException;
import com.masai.Repository.AdminSessionDao;
import com.masai.Repository.PlantDao;
import com.masai.Repository.SessionDao;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Plant;
@Service
public class PlantServiceImpl implements plantService {

	@Autowired
	private PlantDao pdao;
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private AdminSessionDao adminsessioDao;
	@Override
	
	public Plant addPlant(Plant plant,String key) throws PlantException,LoginException {
		
		
        CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to add a plant...");
		}
		
		Plant p = pdao.findByCommonName(plant.getCommonName());
		if(p==null) {
			
			Plant savePlant = pdao.save(plant);
			System.out.println("ok......==");
			return savePlant;
		}else {
			throw new PlantException("Plant already exists...");
		}
		
			
		
		
	}

	@Override
	public Plant updatePlant(Plant plant,String key) throws PlantException ,LoginException{
		
		 CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to update a plant...");
		}
		
		Optional<Plant> opt = pdao.findById(plant.getPlaintId());
		if(opt.isPresent()) {
			
			return pdao.save(plant);
			 
		}else {
			throw new PlantException("No Plant found with Id "+ plant.getPlaintId());
		}
	}

	@Override
	public Plant deletePlant(Integer plantId,String key) throws PlantException,LoginException {
	  
		 CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to delete a Plant...");
		}
		
		
		Optional<Plant> opt = pdao.findById(plantId);
	  if(opt.isPresent()) {
		  Plant p = opt.get();
		  pdao.delete(p);
		  return p;
	  }else {
		  throw new PlantException("No plant found with id "+ plantId);
	  }
	}

	@Override
	public Plant viewPlantById(Integer plantId,String key) throws PlantException,LoginException {
		 CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new LoginException("Please Enter a Valid Key ");
			}
		return pdao.findById(plantId).orElseThrow(()-> new PlantException("No plant found with id "+ plantId));
	}

	@Override
	public Plant viewPlantByCommonName(String name,String key) throws PlantException, LoginException {
		CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		
		Plant plant = pdao.findByCommonName(name);
		if(plant!=null) {
			return plant;
		}else {
			throw new PlantException("No Plant found with Common Name "+ name);
		}
	}

	@Override
	public List<Plant> viewAllPlant(String key) throws PlantException, LoginException {
		CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		List<Plant> plist = pdao.findAll();
		if(plist.size()==0) {
			throw new PlantException("No Plant found...");
		}else {
			return plist;
		}
	}

	@Override
	public List<Plant> viewAllPlantByTypeOfPlant(String type,String key) throws PlantException, LoginException {
		CurrentAdminSession  loggeduser= adminsessioDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key ");
		}
		
		List<Plant > plist = pdao.findAllBytypeOfPlant(type);
		if(plist.size()==0) {
			throw new PlantException("No Plant found...");
		}else {
			return plist;
		}
		
	}

}
