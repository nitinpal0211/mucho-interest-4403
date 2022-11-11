package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Admin;
import com.masai.model.Customer;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Admin findByAdminEmail(String adminEmail);
}
