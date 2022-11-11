package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Plant;
@Repository
public interface PlantDao extends JpaRepository<Plant, Integer> {

	public Plant findByCommonName(String name);
	public List<Plant> findAllBytypeOfPlant(String type);
}
