package com.masai.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	public List<Order>  findByUserid(Integer userid);
	
}