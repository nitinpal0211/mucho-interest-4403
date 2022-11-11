package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.model.OrderDTO;
import com.masai.model.OrderDTO2;
import com.masai.model.Order;
import com.masai.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository repository;
	@Autowired
    PlanterDao planterdao;
	@Autowired
	private SignUpDao signupdao;
	@Autowired
	private PlantDao plantdao;
	@Autowired
	private SeedDao seeddao;
	//	Addingee Order
	
	@Override
	public OrderDTO2 addOrder(OrderDTO order) throws OrderException {
		Order neworder= new Order();
		neworder.setLocalDate(LocalDate.now());
		neworder.setQuantity(1);
		neworder.setTransactionMode(order.getTransactionMode());
		neworder.setUserid(order.getUserid());
		Integer totalcost=0;
		if(order.getPlanterId()!=null) {
		Optional<Planter> planter=planterdao.findById(order.getPlanterId());
		if(!planter.isPresent()) {
			throw new OrderException("No planter found with given id");
		}
		List<Planter> planterlist=new ArrayList<>();
		planterlist.add(planter.get());
		neworder.setPlanters(planterlist);
		totalcost+=planter.get().getPlanterCost();
		
		}
		
		
		if(order.getPlantId()!=null) {
			Optional<Plant> plant=plantdao.findById(order.getPlantId());
			if(!plant.isPresent()) {
				throw new OrderException("No plant found with given id");
			}
			List<Plant> plantlist=new ArrayList<>();
			plantlist.add(plant.get());
			neworder.setPlants(plantlist);
			totalcost+=plant.get().getPlantsCost();
			
			}
		if(order.getSeedId()!=null) {
			Optional<Seed> seed=seeddao.findById(order.getSeedId());
			if(!seed.isPresent()) {
				throw new OrderException("No seed found with given id");
			}
			List<Seed> seedlist=new ArrayList<>();
			seedlist.add(seed.get());
			neworder.setSeeds(seedlist);
			totalcost+=seed.get().getSeedsCost();
			
			}
		
		neworder.setTotalCost(totalcost);
		
		Order od= repository.save(neworder);
		
		OrderDTO2 od2=new OrderDTO2();
		
		od2.setOrderId(od.getOrderId());
		od2.setTotalcost(od.getTotalCost());
		od2.setTransactionMode(od.getTransactionMode());
		od2.setLocaldate(od.getLocalDate());
		od2.setQuantity(1);
		return od2;
		
	}

	
	
	@Override
	public Order updateOrder(Order order) throws OrderException {

		Order od= repository.findById(order.getOrderId())
				.orElseThrow(()-> new OrderException("Currently no Order available with this orderId"));
            
		List<Planter> planters=order.getPlanters();
		List<Plant> plants=order.getPlants();
		List<Seed> seeds=order.getSeeds();
		
		Integer totalcost=0;
		if(planters.size()>0) {
			for(Planter p:planters) {
				totalcost+=p.getPlanterCost();
			}
		}
		
		if(plants.size()>0) {
			for(Plant p:plants) {
				totalcost+=p.getPlantsCost();
			}
		}
		
		
		if(seeds.size()>0) {
			for(Seed s:seeds) {
				totalcost+=s.getSeedsCost();
			}
		}
		od.setTotalCost(totalcost);
		
		return repository.save(od);
	}

	@Override
	public Order deleteOrder(Integer orderId) throws OrderException {
		// TODO Auto-generated method stub
			Order od=repository.findById(orderId).orElseThrow(()->new OrderException("Currently no Order available with this orderId"));
			repository.deleteById(orderId);
			return od;
	}

	@Override
	public Order viewOrder(Integer orderId) throws OrderException {
		// TODO Auto-generated method stub
		Order od=repository.findById(orderId).orElseThrow(()->new OrderException("Currently no Order available with this orderId"));
		return od;
	}

	@Override
	public List<Order> viewAllOrders() throws OrderException {
		// TODO Auto-generated method stub
		List<Order>od= repository.findAll();
		if(od.isEmpty()) {
			throw new OrderException("No order has been orderd");
		}
		
		return od;
	}



	@Override
	public SignUpData viewcustomer(Integer orderid)throws OrderException {
		
		Optional<Orders> orderdata=repository.findById(orderid);
		if(!orderdata.isPresent()) {
			throw new OrderException("no order id found");
		}
		Optional<SignUpData> customer=signupdao.findById(orderdata.get().getUserid());
		
		return customer.get();
	
	}



	@Override
	public List<Order> viewordersbyuserid(Integer userid) throws OrderException {
		
	List<Order> orders=repository.findByUserid(userid);
		if(orders.size()==0) {
			throw new OrderException("User doesn't have any order");
		}
		
		
		return orders;
	}
	

	
}