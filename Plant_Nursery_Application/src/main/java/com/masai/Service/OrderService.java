package com.masai.Service;

import java.util.List;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.OrderException;
import com.masai.model.Orders;
import com.masai.model.OrdersDTO;

public interface OrderService {

	public Orders addOrder(OrdersDTO orders,String key) throws OrderException, CustomerException;
	public Orders updateOrder(OrdersDTO orders,Integer orderId,String key) throws OrderException, CustomerException;
	public String deleteOrder(Integer orderId,String key) throws OrderException, CustomerException;
	public Orders viewOrder(Integer orderId,String key) throws OrderException, CustomerException;
	public List<Orders> viewAllOrder(String key) throws OrderException, CustomerException;
	
	
}
