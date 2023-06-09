package com.acquistionline.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acquistionline.model.Client;
import com.acquistionline.model.Order;
import com.acquistionline.service.InterfaceOrderService;

@RestController
public class OrderController {

	@Autowired
	private InterfaceOrderService orderService;

	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("api/orders")
	public Iterable<Order> getAll() {
		return orderService.getAll();
	}
	
	@RequestMapping("api/clients/{id}/orders")
	public Iterable<Order> getAllOrdersByClientId(@PathVariable String id) {
		return orderService.getAllOrdersByClientId(id);
	}
	
	@RequestMapping("api/orders/{id}")
	public Order getById(@PathVariable int id) {
		
		Optional<Order> foundOrder = orderService.getById(id);
		
		if(foundOrder.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		return foundOrder.get();
	}
	
	@RequestMapping(value = "api/clients/{id}/orders", method = RequestMethod.POST)
	public Order create(@PathVariable String id, @RequestBody Order order) {
		return orderService.create(id, order);
	}
	/*
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.PUT)
	public Client update(@PathVariable String id, @RequestBody Client client) {
		
		Optional<Client> foundClient = clientService.update(id, client);
		
		if(foundClient.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return foundClient.get();
	}
	
	@RequestMapping(value = "api/clients/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable String id) {
		
		boolean isDeleted = clientService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il cliente non è stato trovato!");
		
		return isDeleted;
	}*/
}
