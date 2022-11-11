package com.masai.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.PlantException;
import com.masai.Repository.PlantDao;
import com.masai.model.Plant;
@Service
public class PlantServiceImpl implements plantService {

	@Autowired
	private PlantDao pdao;
	
	@Override
	public Plant addPlant(Plant plant) throws PlantException {
		Plant p = pdao.findByCommonName(plant.getCommonName());
		if(p==null) {
			Plant savePlant = pdao.save(plant);
			return savePlant;
		}else {
			throw new PlantException("Plant already exists...");
		}
		
			
		
		
	}

	@Override
	public Plant updatePlant(Plant plant) throws PlantException {
		Optional<Plant> opt = pdao.findById(plant.getPlaintId());
		if(opt.isPresent()) {
			
			return pdao.save(plant);
			 
		}else {
			throw new PlantException("No Plant found with Id "+ plant.getPlaintId());
		}
	}

	@Override
	public Plant deletePlant(Integer plantId) throws PlantException {
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
	public Plant viewPlantById(Integer plantId) throws PlantException {
		return pdao.findById(plantId).orElseThrow(()-> new PlantException("No plant found with id "+ plantId));
	}

	@Override
	public Plant viewPlantByCommonName(String name) throws PlantException {
		Plant plant = pdao.findByCommonName(name);
		if(plant!=null) {
			return plant;
		}else {
			throw new PlantException("No Plant found with Common Name "+ name);
		}
	}

	@Override
	public List<Plant> viewAllPlant() throws PlantException {
		List<Plant> plist = pdao.findAll();
		if(plist.size()==0) {
			throw new PlantException("No Plant found...");
		}else {
			return plist;
		}
	}

	@Override
	public List<Plant> viewAllPlantByTypeOfPlant(String type) throws PlantException {
		List<Plant > plist = pdao.findAllBytypeOfPlant(type);
		if(plist.size()==0) {
			throw new PlantException("No Plant found...");
		}else {
			return plist;
		}
		
	}

}
