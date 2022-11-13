package com.masai.ServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exceptions.CustomerException;
import com.masai.Exceptions.OrderException;
import com.masai.Repository.CustomerDao;
import com.masai.Repository.OrderDao;
import com.masai.Repository.PlantDao;
import com.masai.Repository.PlanterDao;
import com.masai.Repository.SeedDao;
import com.masai.Repository.SessionDao;
import com.masai.Service.OrderService;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.OrdersDTO;
import com.masai.model.Plant;
import com.masai.model.Planter;
import com.masai.model.Seed;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private PlanterDao planterDao;
	
	@Autowired
	private SeedDao seedDao;
	
	@Autowired
	private PlantDao plantDao;

	@Override
	public Orders addOrder(OrdersDTO orders, String key) throws CustomerException, OrderException{

		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to placing the order.");
		}
		
		  Customer customer=  customerDao.findByCustomerEmail(orders.getCustomerEmail());
		
		  if(customer==null)
		  {
			  throw new CustomerException("Invalid Customer Email details , Please login first for placing the order. ");
		  }
		  
		if(customer.getCustomerId()==loggeduser.getUserId())
		{
			  Orders orderDetails = new Orders();
			  orderDetails.setLocalDate(LocalDate.now());
			  if(orders.getTransactionMode()==null)
			  {
				  throw new OrderException("please enter payment gateway like UPI or Waller or Net Banking.");
			  }
			  if(orders.getQuantity()<=0)
			  {
				  throw new OrderException("please add one for placing the order.");
			  }
			  orderDetails.setTransactionMode(orders.getTransactionMode());
			  orderDetails.setQuantity(orders.getQuantity());
			  orderDetails.setCustomerId(customer.getCustomerId());
			  double total=0;
			 Optional<Planter> planter =planterDao.findById(orders.getPlanterId());
			 Optional<Plant> plant =plantDao.findById(orders.getPlantId());
			 Optional<Seed> seed =seedDao.findById(orders.getSeedId());
			 
			 if(!(planter.isPresent()&&plant.isPresent()&&seed.isPresent()))
			 {
				 throw new OrderException("Invalid product Id , put right Product Id , Please add one product to place the order.");
			 }
			 
			 if(planter.isPresent())
			 {
				 
				 Set<Planter> list= new HashSet<>();
				 System.out.println("before1 ==========="+planter.get().getPlanterStock());
				 if(orders.getQuantity()<=planter.get().getPlanterStock())
				 {
					 list.add(planter.get());
					 orderDetails.setOrderPlanters(list);
					 planter.get().setPlanterStock(planter.get().getPlanterStock()-orders.getQuantity());
					 planterDao.save(planter.get());
					 total=total+orders.getQuantity()*planter.get().getPlanterCost();
				 }
				 else throw new OrderException("Insufficient stock for planters ");
				 System.out.println("after1 **************"+planter.get().getPlanterStock());
			 }
			 
			
			 if(plant.isPresent())
			 {
				 
				 Set<Plant> list= new HashSet<>();
				 System.out.println("before ==========="+plant.get().getPlantStock());
				 if(orders.getQuantity()<=plant.get().getPlantStock())
				 {
					 list.add(plant.get());
					 orderDetails.setOrderPlants(list);
					 plant.get().setPlantStock(plant.get().getPlantStock()-orders.getQuantity());
					 plantDao.save(plant.get());
					 total=total+orders.getQuantity()*plant.get().getPlantCost();
				 }
				 else
				  throw new OrderException("Insufficient stock for plants ");
				 System.out.println("*********************");
				
			 }
			 
			 
			 if(seed.isPresent())
			 {
				 
				 Set<Seed> list= new HashSet<>();
				 System.out.println("before2 ==========="+seed.get().getSeedsStock());
				 if(orders.getQuantity()<=seed.get().getSeedsStock())
				 {
					 list.add(seed.get());
					 orderDetails.setOrderSeeds(list);
					 seed.get().setSeedsStock(seed.get().getSeedsStock()-orders.getQuantity());
					 seedDao.save(seed.get());
					 total=total+orders.getQuantity()*seed.get().getSeedsCost();
				 }
				 else throw new OrderException("Insufficient stock for Seeds ");
				 System.out.println("after **************"+seed.get().getSeedsStock());
				 
			  }
			 
			 orderDetails.setTotalCost(total);
			 System.out.println("order add is end. *************===========*********"+orderDetails);
			 orderDao.save(orderDetails);
			 System.out.println("order return state. *************===========*********"+orderDetails);
			 return orderDetails;
			 
		}
		
		throw new CustomerException("Please login first for placing the order. ");
		
	}

	@Override
	public Orders updateOrder(OrdersDTO orders,Integer orderId, String key) throws OrderException, CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to updating the order.");
		}
		
		  Customer customer=  customerDao.findByCustomerEmail(orders.getCustomerEmail());
		
		  if(customer==null)
		  {
			  throw new CustomerException("Invalid Customer Email details , Please login first for updating the order. ");
		  }
		  
		  if(customer.getCustomerId()==loggeduser.getUserId())
			{
			  
			      Optional<Orders> opt= orderDao.findById(orderId);
			      if(opt.isPresent())
			      {
			    	  Orders orderDetails =opt.get();
					  orderDetails.setLocalDate(LocalDate.now());
					  if(orders.getTransactionMode()==null)
					  {
						  throw new OrderException("please enter payment gateway like UPI or Wallet or Net Banking.");
					  }
					  if(orders.getQuantity()<=0)
					  {
						  throw new OrderException("please add one product for updating the order.");
					  }
					  orderDetails.setTransactionMode(orders.getTransactionMode());
					 
					  orderDetails.setCustomerId(customer.getCustomerId());
					  double total=0;
					 Optional<Planter> planter =planterDao.findById(orders.getPlanterId());
					 Optional<Plant> plant =plantDao.findById(orders.getPlantId());
					 Optional<Seed> seed =seedDao.findById(orders.getSeedId());
					 
					 if(!(planter.isPresent()||plant.isPresent()||seed.isPresent()))
					 {
						 throw new OrderException("Invalid product Id , put right Product Id , Please add one product to place the order.");
					 }
					 if(planter.isPresent())
					 {
						 
						 Set<Planter> list= new HashSet<>();
						 
						 if(orders.getQuantity()<=planter.get().getPlanterStock())
						 {
							 list.add(planter.get());
							 orderDetails.setOrderPlanters(list);
							 Planter p =planter.get();
							 p.setPlanterStock(planter.get().getPlanterStock()+orderDetails.getQuantity()-orders.getQuantity());
							 planterDao.save(p);
							 total=total+orders.getQuantity()*planter.get().getPlanterCost();
						 }
						 else throw new OrderException("Insufficient stock for planters ");
						
					 }
					 
					
					 if(plant.isPresent())
					 {
						 
						 Set<Plant> list= new HashSet<>();
						 
						 if(orders.getQuantity()<=plant.get().getPlantStock())
						 {
							 list.add(plant.get());
							 orderDetails.setOrderPlants(list);
							 Plant p =plant.get();
							 p.setPlantStock(plant.get().getPlantStock()+orderDetails.getQuantity()-orders.getQuantity());
							 plantDao.save(p);
							 total=total+orders.getQuantity()*plant.get().getPlantCost();
						 }
						 else throw new OrderException("Insufficient stock for plants ");
						 
						
					 }
					 
					 
					 if(seed.isPresent())
					 {
						 
						 Set<Seed> list= new HashSet<>();
						 
						 if(orders.getQuantity()<=seed.get().getSeedsStock())
						 {
							 list.add(seed.get());
							 orderDetails.setOrderSeeds(list);
							 Seed s = seed.get();
							 s.setSeedsStock(seed.get().getSeedsStock()+orderDetails.getQuantity()-orders.getQuantity());
							 seedDao.save(s);
							 total=total+orders.getQuantity()*seed.get().getSeedsCost();
						 }
						 else throw new OrderException("Insufficient stock for Seeds ");
						 
						 
					 }
					 orderDetails.setQuantity(orders.getQuantity());
					 orderDetails.setTotalCost(total);
					 orderDao.save(orderDetails);
					 return orderDetails;
			      }
			  
				  
				 
			}
			
			throw new CustomerException("Please login first for updating the order. "); 
	}

	@Override
	public String deleteOrder(Integer orderId, String key) throws OrderException, CustomerException {
		
		 CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new CustomerException("Please login first and Enter a Valid Key to deleting the order.");
			}
			
			  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
			  
			  Optional<Orders> orders  = orderDao.findById(orderId);
			
			  if(opt.isPresent())
			  {
				  if(orders.isPresent())
				  {
					  if(orders.get().getCustomerId()==opt.get().getCustomerId())
					  {
						  orderDao.delete(orders.get());
						  
						  return "Order deleted successfully.";
					  }
					  throw new CustomerException("invalid key , Please login first for deleting the order. ");
				  }
				  throw new OrderException("No order found with this order id "+orderId);
			  }
			  throw new CustomerException("Invalid key details , Please login first for deleting the order. ");
	}

	@Override
	public Orders viewOrder(Integer orderId, String key) throws OrderException, CustomerException {
		
		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to viewing the order.");
		}
		
		  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
		  
		  Optional<Orders> orders  = orderDao.findById(orderId);
		
		  if(opt.isPresent())
		  {
			  if(orders.isPresent())
			  {
				  if(orders.get().getCustomerId()==opt.get().getCustomerId())
				  {
					  return orders.get();
				  }
				  throw new CustomerException("invalid key , Please login first for viewing the order. ");
			  }
			  throw new OrderException("No order found with this order id "+orderId);
		  }
		  throw new CustomerException("Invalid key details , Please login first for viewing the order. ");
	}

	@Override
	public List<Orders> viewAllOrder(String key) throws OrderException, CustomerException {
CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to viewing the order.");
		}
		
		  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
		  
		  List<Orders> orders  = orderDao.findAll();
		
		  if(opt.isPresent())
		  {
			  if(orders.size()!=0)
			  {
				  List<Orders> myorders = new ArrayList<>();
				  for(Orders el : orders)
				  {
					  if(el.getCustomerId()==opt.get().getCustomerId())
					  {
						  myorders.add(el);
					  }
				  }
				  if(myorders.size()==0)
				  {
					  throw new OrderException("No order found in your account ");
				  }
				  
				  return myorders;
				  
			  }
			  throw new OrderException("No order found  ");
		  }
		  throw new CustomerException("Invalid key details , Please login first for viewing All your order. ");
	}

	
}
