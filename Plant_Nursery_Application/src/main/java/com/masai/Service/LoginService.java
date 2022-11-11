package com.masai.Service;

import com.masai.Exceptions.LoginException;
import com.masai.model.AdminDTO;
import com.masai.model.CustomerDTO;

public interface LoginService {

	public String logIntoAccount(CustomerDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;
	
	public String logAdminIntoAccount(AdminDTO dto)throws LoginException;

	public String logOutAdminFromAccount(String key)throws LoginException;
	
}
