 package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Repository.AdminDao;
import com.masai.Repository.AdminSessionDao;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.AdminService;
import com.masai.model.CurrentAdminSession;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	
	// this method will add a customer while taking customer details and key as a parameter 
	@Override
	public Customer addCustomer(Customer customer, String key) throws CustomerException {
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new CustomerException("Invalid admin key ,you are not admin");
		}
		
        Customer existingCustomer = customerDao.findByCustomerEmail(customer.getCustomerEmail());
		
		//System.out.println("existingCustomer : ====================== "+existingCustomer);
		if(existingCustomer!=null)
		{
			throw new CustomerException("Customer already exists with this Email : "+customer.getCustomerEmail());
		}
		
		return customerDao.save(customer);
	}
	
	// this method will update a customer while taking customer details and key as a parameter 
	@Override
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException {
        
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new CustomerException("Admin not login , Please Enter a Valid Key to update a customer.");
		}
		Customer existingCustomer = customerDao.findByCustomerEmail(tenant.getCustomerEmail());
		
		if(existingCustomer!=null)
		{
			return customerDao.save(tenant);
			
		}
		
		throw new CustomerException("Invalid Customer details ");
		
	}

	// this method will delete a customer while taking customer email and key as a parameter 
	@Override
	public Customer deleteCustomer(String email, String Key) throws CustomerException {
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(Key);
		
		if(loggedAdmin==null)
		{
			throw new CustomerException("Admin not login , Please Enter a Valid Key to delete a customer.");
		}
			
		Customer existingCustomer = customerDao.findByCustomerEmail(email);
		
			if(existingCustomer!=null)
			{
				 customerDao.delete(existingCustomer);
				 
				 sessionDao.deleteById(existingCustomer.getCustomerId());
				 return existingCustomer;
				
			}
			
			throw new CustomerException("Invalid Customer details ");

	}

	// this method will get a customer while taking customer id and key as a parameter 
	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException {
		
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
			
			if(loggedAdmin==null)
			{
				throw new CustomerException("Admin not login , Please Enter a Valid Key to view customer.");
			}
				
			Optional<Customer> opt = customerDao.findById(customerId);
			
				if(opt.isPresent())
				{
					 
					 return opt.get();
					
				}
				
				throw new CustomerException("Invalid Customer Id details ");
	}

	// this method will show all customers while taking key as a parameter
	@Override
	public List<Customer> viewAllCustomers(String key) throws CustomerException {
		 
		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
			
			if(loggedAdmin==null)
			{
				throw new CustomerException("Admin not login , Please Enter a Valid Key to view All Customers.");
			}
				
			List<Customer> opt = customerDao.findAll();
			
				if(opt.size()!=0)
				{
					 
					 return opt;
					
				}
				
				throw new CustomerException("No Customer  details found.");
	}

	// this method will check validation of customer while taking userName,password and key as a parameter
	@Override
	public Customer validateCustomer(String userName, String password, String key) throws CustomerException {

		CurrentAdminSession  loggedAdmin= adminSessionDao.findByUuid(key);
		
		if(loggedAdmin==null)
		{
			throw new CustomerException("Admin not login , Please Enter a Valid Key to validate  a customer.");
		}
			
		Customer existingCustomer = customerDao.findByCustomerEmail(userName);
		
			if(existingCustomer!=null)
			{
				 if(existingCustomer.getCustomerPassword().equals(password))
				 {
					 return existingCustomer;
				 }
				 throw new CustomerException("Invalid Customer password details ");
				
			}
			
			throw new CustomerException("Invalid Customer Email details ");

	}

}
