package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlanterException;
import com.masai.model.CurrentAdminSession;
import com.masai.model.Planter;
import com.masai.Repository.AdminDao;
import com.masai.Repository.AdminSessionDao;
import com.masai.Repository.PlanterDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.PlanterService;

@Service
public class PlanterServiceImpl implements PlanterService{

	@Autowired
	private PlanterDao planterDao;
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	// this method will add a planter
	@Override
	public Planter addPlanter(Planter planter,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		
	   Planter aPlanter = planterDao.save(planter);
	  
		 if(aPlanter == null ) {
			 throw new PlanterException("please enter valid details");
		 }
	  
		 return aPlanter;
		
	}

	// this method will update a planter
	@Override
	public Planter updatePlanter(Planter planter,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		
		Integer planterId = planter.getPlanterId();
		
		Optional<Planter> uPlanter = planterDao.findById(planterId);		
		
		if(uPlanter.isPresent()) {
			return planterDao.save(planter);
		}
		else
			throw  new PlanterException("Please enter valid PlanterId to update planter");
		
		
	}

	// this method will delete a planter
	@Override
	public Planter deletePlanter(Integer planterId,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		
		
		Optional<Planter> found = planterDao.findById(planterId);		
		
		
		if(found.isPresent()) {
			Planter planter=found.get();
			planterDao.delete(planter);
			return planter;
		}
		else
			throw  new PlanterException("Please enter valid planterId to delete planter");
	}

	// this method will get a particular planter details
	@Override
	public Planter viewPlanter(Integer planterId,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		
		Optional<Planter> found = planterDao.findById(planterId);		
		
		
		return found.orElseThrow( ()-> new PlanterException("Unable to find Planter with id "+planterId));
		
		
	}

	// this method will return particular planters
	@Override
	public List<Planter> viewPlanter(String planterShape,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
        List<Planter> planteraByShape;
		
        planteraByShape = planterDao.findByPlanterShape(planterShape);
		
		if(planteraByShape.size()!=0)
		     return planteraByShape;
		else
		     throw  new PlanterException("planter with this plantershape does not exist try different plantershape name ");
	}

	// this planter will return particular planters
	@Override
	public List<Planter> viewAllPlanters(String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		List<Planter> allPlanters;
		allPlanters = planterDao.findAll();
		
		if(allPlanters.size()!=0)
		     return allPlanters;
		else
		     throw  new PlanterException("Right now we dont have any planters please check Seeds and plant");
	}

	@Override

	// this method will view return all planters
	public List<Planter> viewAllPlanters(Double minCost, Double maxCost,String key) throws PlanterException, LoginException {
		// TODO Auto-generated method stub
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new LoginException("Invalid admin key ,you are not admin");
		}
		List<Planter> findPlanterBeetween;
		findPlanterBeetween = planterDao.findByPlanterCostBetween(minCost, maxCost);

		
		if(findPlanterBeetween.size()!=0)
		      return findPlanterBeetween;
		else
			  throw  new PlanterException("No planter exist in this Range");
	
	}

}

