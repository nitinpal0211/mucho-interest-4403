package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Seed;
@Repository
public interface SeedDao extends JpaRepository<Seed, Integer> {

}
