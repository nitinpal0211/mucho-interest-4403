package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.PlanterException;
import com.masai.model.Planter;
import com.masai.Repository.PlanterDao;
import com.masai.Service.PlanterService;

@Service
public class PlanterServiceImpl implements PlanterService{

	@Autowired
	private PlanterDao planterDao;
	
	@Override
	public Planter addPlanter(Planter planter) throws PlanterException {
		// TODO Auto-generated method stub
		
	   Planter aPlanter = planterDao.save(planter);
	  
		 if(aPlanter == null ) {
			 throw new PlanterException("please enter valid details");
		 }
	  
		 return aPlanter;
		
	}

	@Override
	public Planter updatePlanter(Planter planter) throws PlanterException {
		// TODO Auto-generated method stub
		Integer planterId = planter.getPlanterId();
		
		Optional<Planter> uPlanter = planterDao.findById(planterId);		
		
		if(uPlanter.isPresent()) {
			return planterDao.save(planter);
		}
		else
			throw  new PlanterException("Please enter valid PlanterId to update planter");
		
		
	}

	@Override
	public Planter deletePlanter(Integer planterId) throws PlanterException {
		// TODO Auto-generated method stub
		
		Optional<Planter> found = planterDao.findById(planterId);		
		
		
		if(found.isPresent()) {
			Planter planter=found.get();
			planterDao.delete(planter);
			return planter;
		}
		else
			throw  new PlanterException("Please enter valid planterId to delete planter");
	}

	@Override
	public Planter viewPlanter(Integer planterId) throws PlanterException {
		// TODO Auto-generated method stub
		
		
		Optional<Planter> found = planterDao.findById(planterId);		
		
		
		return found.orElseThrow( ()-> new PlanterException("Unable to find Planter with id "+planterId));
		
		
	}

	@Override
	public List<Planter> viewPlanter(String planterShape) throws PlanterException {
		// TODO Auto-generated method stub
        List<Planter> planteraByShape;
		
        planteraByShape = planterDao.findByPlanterShape(planterShape);
		
		if(planteraByShape.size()!=0)
		     return planteraByShape;
		else
		     throw  new PlanterException("planter with this plantershape does not exist try different plantershape name ");
	}

	@Override
	public List<Planter> viewAllPlanters() throws PlanterException {
		// TODO Auto-generated method stub
		List<Planter> allPlanters;
		allPlanters = planterDao.findAll();
		
		if(allPlanters.size()!=0)
		     return allPlanters;
		else
		     throw  new PlanterException("Right now we dont have any planters please check Seeds and plant");
	}

	@Override
	public List<Planter> viewAllPlanters(Double minCost, Double maxCost) throws PlanterException {
		// TODO Auto-generated method stub
		List<Planter> findPlanterBeetween = planterDao.findByPlanterCostBetween(minCost, maxCost);
		
		if(findPlanterBeetween.size()!=0)
		      return findPlanterBeetween;
		else
			  throw  new PlanterException("No planter exist in this Range");
	
	}

}

