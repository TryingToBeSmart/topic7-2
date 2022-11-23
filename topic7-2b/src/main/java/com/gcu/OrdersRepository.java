package com.gcu;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<OrderEntity, String> {
	public OrderEntity getOrdersById(String id);
}
