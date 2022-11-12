package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Plant;
import com.masai.model.Seed;
@Repository
public interface SeedDao extends JpaRepository<Seed, Integer> {

	
	public Seed findByCommonName(String name);
	public List<Seed> findAllBytypeOfSeeds(String type);
}
