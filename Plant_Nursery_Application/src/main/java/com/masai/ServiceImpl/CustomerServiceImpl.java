package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.PlantDao;
import com.masai.Repository.PlanterDao;
import com.masai.Repository.SeedDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.CustomerService;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private PlanterDao planterDao;
	
	@Autowired
	private SeedDao seedDao;
	
	@Autowired
	private PlantDao plantDao;

	// this method will add a customer 
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
//		System.out.println("customer : ************************* "+customer);
		
		Customer existingCustomer = customerDao.findByCustomerEmail(customer.getCustomerEmail());
		
		//System.out.println("existingCustomer : ====================== "+existingCustomer);
		if(existingCustomer!=null)
		{
			throw new CustomerException("Customer already exists with this Email : "+customer.getCustomerEmail());
		}
		
		return customerDao.save(customer);
	}

	// this method will update a customer
	@Override
	public Customer upadateCustomer(Customer tenant ,String Key) throws CustomerException {
		
		CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to update a customer.");
		}
		
		if(tenant.getCustomerId()==loggeduser.getUserId())
		{
			return customerDao.save(tenant);
			
		}
		
		throw new CustomerException("Invalid Customer details , Please login first for updating the customer. ");
		
	}

	// this method will delete a customer
	@Override
	public Customer deleteCustomer(String email, String Key) throws CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
 		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to delete customer account.");
		}
		
		Customer existingCustomer = customerDao.findByCustomerEmail(email);
		
		if(existingCustomer==null)
		{
			throw  new CustomerException("Invalid Customer Email details ");
		}
		
		if(existingCustomer.getCustomerId()==loggeduser.getUserId())
		{
			 customerDao.delete(existingCustomer);
			 
			 sessionDao.delete(loggeduser);
			 return existingCustomer;
			
		}
		else throw new CustomerException("Please login first for deleting account.");
	}

	// this method will return all plants 
	@Override
	public List<Plant> viewAllPlants(String Key) throws CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to update a customer.");
		}
		
		List<Plant> plants= plantDao.findAll();
		
		if(plants.size()==0)
		{
			throw new CustomerException("No plants available.");
		}
		
		return plants;
		
		
	}

	// this method will return all planters
	@Override
	public List<Planter> viewAllPlanters(String Key) throws CustomerException {


        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to update a customer.");
		}
		
		
		 
		 List<Planter> plants= planterDao.findAll();
			
			if(plants.size()==0)
			{
				throw new CustomerException("No planters available.");
			}
			
			return plants;

		
	}
	
	//this method will return all seeds
	@Override
	public List<Seed> viewAllSeeds(String Key) throws CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to update a customer.");
		}
		
		 
		 
		 List<Seed> plants= seedDao.findAll(); 
			
			if(plants.size()==0)
			{
				throw new CustomerException("No Seeds available.");
			}
			
			return plants;
		
	}

	
	
}
