package com.masai.Service;

import com.masai.Exceptions.LoginException;
import com.masai.model.AdminDTO;
import com.masai.model.CustomerDTO;

public interface LoginService {

	// this method log in the account
	public String logIntoAccount(CustomerDTO dto)throws LoginException;

	// this method will log out from account
	public String logOutFromAccount(String key)throws LoginException;
	
	// this method will log Admin into account
	public String logAdminIntoAccount(AdminDTO dto)throws LoginException;

	// this method will log out Admin from account
	public String logOutAdminFromAccount(String key)throws LoginException;
	
}
