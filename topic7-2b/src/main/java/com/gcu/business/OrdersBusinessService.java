package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.OrderEntity;
import com.gcu.OrderModel;
import com.gcu.OrdersRepository;


/**
 * Add a new Business service class named OrdersBusinessService in the
com.gcu.business package. The class should be marked with the @Service
annotation. Auto-wire the OrdersRepository into variable named service using
constructor injection. Add a new method public List<OrderModel>
getAllOrders() that gets all orders from the database using the findAll() method on
the ordersRepository variable. Convert the list of OrderEntity to a list of
OrderModel and return the list of OrderModel.

 * @author jjk14
 *
 */
@Service
public class OrdersBusinessService {
	
	@Autowired
	OrdersRepository ordersRepository;
	
	//non-default constructor for constructor injection
	public OrdersBusinessService(OrdersRepository ordersRepository) {
		super();
		this.ordersRepository = ordersRepository;
	}

	//get all orders from the DB
	public List<OrderModel> getAllOrders(){
		//get all entity orders from DB
		List<OrderEntity> orderEntity = ordersRepository.findAll();
		
		//create list of Domain orders
		List<OrderModel> orderDomain = new ArrayList<OrderModel>();
		for(OrderEntity entity : orderEntity) {
			orderDomain.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity()));
		}
		return orderDomain;
	}
}
