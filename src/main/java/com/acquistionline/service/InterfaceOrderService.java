package com.acquistionline.service;

import java.util.Optional;

import com.acquistionline.model.Order;

public interface InterfaceOrderService {
	
	public Iterable<Order> getAll();

	public Iterable<Order> getAllOrdersByClientId(String id);
	
	public Iterable<Order> getAllOrdersByProductId(String id);
	
	public Optional<Order> getById(int id);
	
	public Order create(String idc, String idp, Order order);
	
	public Optional<Order> update(int id, Order order);
	
	public boolean delete(int id);
		
	public double getTotPriceByClientId(String id);
}
