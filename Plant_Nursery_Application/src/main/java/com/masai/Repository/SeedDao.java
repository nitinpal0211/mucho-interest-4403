package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Seed;

public interface SeedDao extends JpaRepository<Seed, Integer> {

}
