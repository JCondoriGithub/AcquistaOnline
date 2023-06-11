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
	
	@RequestMapping("api/products/{id}/orders")
	public Iterable<Order> getAllOrdersByProductId(@PathVariable String id) {
		return orderService.getAllOrdersByProductId(id);
	}
	
	@RequestMapping("api/orders/{id}")
	public Order getById(@PathVariable int id) {
		
		Optional<Order> foundOrder = orderService.getById(id);
		
		if(foundOrder.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		return foundOrder.get();
	}
	
	@RequestMapping(value = "api/clients/{idc}/products/{idp}/orders", method = RequestMethod.POST)
	public Order create(@PathVariable String idc, @PathVariable String idp, @RequestBody Order order) {
		return orderService.create(idc, idp, order);
	}
	
	@RequestMapping(value = "api/orders/{id}", method = RequestMethod.PUT)
	public Order update(@PathVariable int id, @RequestBody Order order) {
		
		Optional<Order> foundOrder = orderService.update(id, order);
		
		if(foundOrder.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		return foundOrder.get();
	}
	
	@RequestMapping(value = "api/orders/{id}", method = RequestMethod.DELETE)
	public boolean delete(@PathVariable int id) {
		
		boolean isDeleted = orderService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		return isDeleted;
	}
}
