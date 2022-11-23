package com.gcu;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class creates objects as they are read from the database
 * @author jjk14
 *
 */
@Document(collection="orders")
public class OrderEntity {

	@Id
	String id;
	
	@Indexed(unique=true)
	String orderNo;
	
	@Indexed(unique=true)
	String productName;
	
	float price;
	int quantity;
	
	
	public OrderEntity() {
	}
	
	public OrderEntity(String id, String orderNo, String productName, float price, int quantity) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
