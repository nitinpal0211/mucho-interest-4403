package com.masai.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.LoginException;
import com.masai.Repository.AdminDao;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.LoginService;
import com.masai.model.Admin;
import com.masai.model.AdminDTO;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public String logIntoAccount(CustomerDTO dto) throws LoginException {
		
		Customer user = customerDao.findByCustomerEmail(dto.getCustomerEmail());
		
		if(user==null)
		{
			throw new LoginException("Please Enter valid Email");
		}
		
		Optional<CurrentUserSession> opt = sessionDao.findById(user.getCustomerId());
		
		if(opt.isPresent())
		{
			throw new LoginException("User Already logged in ");
		}
		
		if(user.getCustomerPassword().equals(dto.getCustomerPassword()))
		{
			String key = RandomString.make(7);
			
			CurrentUserSession cus = new CurrentUserSession(user.getCustomerId(),key,LocalDateTime.now());
			
			sessionDao.save(cus);
			
			return user.toString();
		}
		else
		{
			throw new LoginException("Please Enter a valid password.");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		
	     CurrentUserSession cus =	sessionDao.findByUuid(key);
	     
	     if(cus==null)
	     {
	    	 throw new LoginException("User not Login with this Email.");
	     }
	     
	     sessionDao.delete(cus);
	     
	     return "Logged out Successfully.";
	}

	@Override
	public String logAdminIntoAccount(AdminDTO dto) throws LoginException {

		Admin user = adminDao.findByAdminEmail(dto.getAdminEmail());
		
		if(user==null)
		{
			throw new LoginException("Please Enter valid Email");
		}
		
		Optional<CurrentUserSession> opt = sessionDao.findById(user.getAdminId());
		
		if(opt.isPresent())
		{
			throw new LoginException("Admin Already logged in ");
		}
		
		if(user.getAdminPassword().equals(dto.getAdminPassword()))
		{
			String key = RandomString.make(7);
			
			CurrentUserSession cus = new CurrentUserSession(user.getAdminId(),key,LocalDateTime.now());
			
			sessionDao.save(cus);
			
			return user.toString();
		}
		else
		{
			throw new LoginException("Please Enter a valid password.");
		}
	}

	@Override
	public String logOutAdminFromAccount(String key) throws LoginException {

		 CurrentUserSession cus =	sessionDao.findByUuid(key);
	     
	     if(cus==null)
	     {
	    	 throw new LoginException("Admin not Login with this Email.");
	     }
	     
	     sessionDao.delete(cus);
	     
	     return "Logged out Successfully.";
	}
}
