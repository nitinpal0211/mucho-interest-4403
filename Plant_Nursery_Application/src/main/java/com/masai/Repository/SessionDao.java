package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentUserSession;
import com.masai.model.CustomerDTO;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

	public  CurrentUserSession  findByUuid(String uuid);
}
