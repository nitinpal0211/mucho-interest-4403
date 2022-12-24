package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.LoginException;
import com.masai.Exceptions.PlanterException;
import com.masai.model.Planter;

public interface PlanterService {

	// this method add a planter
	public Planter addPlanter(Planter planter,String key) throws PlanterException, LoginException;

	// this method will update a planter
	public Planter updatePlanter(Planter planter,String key) throws PlanterException, LoginException;

	// this method will delete a planter
	public Planter deletePlanter(Integer planterId,String key) throws PlanterException, LoginException;

	// this method will get a particular planter details
	public Planter viewPlanter(Integer planterId,String key) throws PlanterException, LoginException;

	// this method will return particular planters
	public List<Planter> viewPlanter(String planterShape,String key) throws PlanterException, LoginException;

	// this method will return particular planters
	public List<Planter> viewAllPlanters(String key) throws PlanterException, LoginException;

    // this method will all planters 
	public List<Planter> viewAllPlanters(Double minCost, Double maxCost,String key) throws PlanterException, LoginException;


	
}
