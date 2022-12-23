package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.OrderException;
import com.masai.model.Orders;
import com.masai.model.OrdersDTO;

public interface OrderService {

	// this method will add a order
	public Orders addOrder(OrdersDTO orders,String key) throws OrderException, CustomerException;
	
	// this method will update a order
	public Orders updateOrder(OrdersDTO orders,Integer orderId,String key) throws OrderException, CustomerException;
	
	// this method will delete a order
	public String deleteOrder(Integer orderId,String key) throws OrderException, CustomerException;
	
	// this method will get a order details
	public Orders viewOrder(Integer orderId,String key) throws OrderException, CustomerException;
	
	// this method will return all orders
	public List<Orders> viewAllOrder(String key) throws OrderException, CustomerException;
	
	
}
