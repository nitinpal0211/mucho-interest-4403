package com.masai.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.Exceptions.OrderException;
import com.masai.model.OrderDTO;
import com.masai.model.OrderDTO2;
import com.masai.model.Order ;


@Service
public interface OrderService {
	public OrderDTO2 addOrder(OrderDTO order) throws OrderException;
	public Order updateOrder(Order order) throws OrderException;
	public Order deleteOrder(Integer orderId) throws OrderException;
	public Order viewOrder(Integer orderId) throws OrderException;
	public List<Order>viewAllOrders() throws OrderException;
	//public SignUpData viewcustomer(Integer orderid) throws OrderException;
	
	public List<Order> viewordersbyuserid(Integer userid)throws OrderException;
	
	
	
}