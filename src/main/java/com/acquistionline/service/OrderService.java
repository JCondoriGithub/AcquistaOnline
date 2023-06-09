package com.acquistionline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquistionline.model.Client;
import com.acquistionline.model.Order;
import com.acquistionline.repository.InterfaceClientRepo;
import com.acquistionline.repository.InterfaceOrderRepo;

@Service
public class OrderService implements InterfaceOrderService {
	
	@Autowired
	private InterfaceClientRepo clientRepo;
	
	@Autowired
	private InterfaceOrderRepo orderRepo;

	public OrderService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Iterable<Order> getAll() {
		return orderRepo.findAll();
	}

	@Override
	public Iterable<Order> getAllOrdersByClientId(String id) {
		return orderRepo.findByClientCode(id);
	}

	@Override
	public Optional<Order> getById(int id) {
		return orderRepo.findById(id);
	}

	@Override
	public Order create(String id, Order order) {
		
		Client client = clientRepo.findById(id).get();
		
		order.setClient(client);
		
		return orderRepo.save(order);
	}

	@Override
	public Optional<Order> update(int id, Order order) {

		Optional<Order> foundOrder =orderRepo.findById(id);
		
		if(foundOrder.isEmpty())
			return Optional.empty();
		
		if(order.getPaymentType() != null)
			foundOrder.get().setPaymentType(order.getPaymentType());
		
		if(order.getQtyProduct() != 0)
			foundOrder.get().setQtyProduct(order.getQtyProduct());
		
		orderRepo.save(foundOrder.get());
		
		return foundOrder;
	}

	@Override
	public boolean delete(int id) {

		Optional<Order> foundOrder = orderRepo.findById(id);
		
		if(foundOrder.isEmpty())
			return false;
		
		orderRepo.delete(foundOrder.get());
		return true;
	}

	@Override
	public void deleteAllOrdersByClientId(String id) {
		orderRepo.deleteByClientId(id);
	}
}
