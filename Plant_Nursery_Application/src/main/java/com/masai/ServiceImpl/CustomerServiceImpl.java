package com.masai.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.CustomerService;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;

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

	@Override
	public Customer deleteCustomer(Customer customer, String Key) throws CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
 		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to delete customer account.");
		}
		
		if(customer.getCustomerId()==loggeduser.getUserId())
		{
			 customerDao.delete(customer);
			 
			 sessionDao.delete(loggeduser);
			 return customer;
			
		}
		
		throw new CustomerException("Invalid Customer details , Please login first for deleting account.");
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer validateCustomer(String userName, String password) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
