package com.acquistionline.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.acquistionline.model.Order;
import com.acquistionline.model.Product;
import com.acquistionline.service.InterfaceOrderService;
import com.acquistionline.service.InterfaceProductService;

@RestController
public class OrderController {

	@Autowired
	private InterfaceOrderService orderService;
	
	@Autowired
	private InterfaceProductService productService;

	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("api/orders")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getAll() {
		return orderService.getAll();
	}
	
	@RequestMapping("api/clients/{id}/orders")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getAllOrdersByClientId(@PathVariable String id) {
		return orderService.getAllOrdersByClientId(id);
	}
	
	@RequestMapping("api/products/{id}/orders")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Order> getAllOrdersByProductId(@PathVariable String id) {
		return orderService.getAllOrdersByProductId(id);
	}
	
	@RequestMapping("api/orders/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Order getById(@PathVariable int id) {
		
		Optional<Order> foundOrder = orderService.getById(id);
		
		if(foundOrder.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		return foundOrder.get();
	}
	
	@RequestMapping(value = "api/clients/{idc}/products/{idp}/orders", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Order create(@PathVariable String idc, @PathVariable String idp, @RequestBody Order order) {
		
		Optional<Product> foundProduct = productService.getById(idp);
		
		if(foundProduct.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "il prodotto selezionato non è disponibile!");

		if(order.getQtyProduct() > foundProduct.get().getQtyAvailable())
			throw new ResponseStatusException(HttpStatus.CONFLICT, "la quantità richiesta del prodotto supera quella disponibile!");
		
		for(Order o: orderService.getAllOrdersByClientId(idc)) {
		
			if(o.getProduct().getId() == idp)
				throw new ResponseStatusException(HttpStatus.CONFLICT, "hai già un'ordine per quel prodotto!");
		}
		
		return orderService.create(idc, idp, order);
	}
	
	@RequestMapping(value = "api/orders/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Order update(@PathVariable int id, @RequestBody Order order) {
		
		Optional<Order> foundOrder = orderService.getById(id);
		
		if(foundOrder.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
		Optional<Product> foundProduct = productService.getById(foundOrder.get().getProduct().getId());
		
		if(order.getQtyProduct() > foundProduct.get().getQtyAvailable() + foundOrder.get().getQtyProduct())
			throw new ResponseStatusException(HttpStatus.CONFLICT, "la quantità richiesta del prodotto supera quella disponibile!");
		
		foundOrder = orderService.update(id, order);
		
		return foundOrder.get();
	}
	
	@RequestMapping(value = "api/orders/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		
		boolean isDeleted = orderService.delete(id);
		
		if(!isDeleted)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "l'ordine non è stato trovato!");
		
	}
	
	@RequestMapping(value = "api/clients/{id}/orders/products")
	@ResponseStatus(HttpStatus.OK)
	public double getTotPriceByClientId(@PathVariable String id) {
		return orderService.getTotPriceByClientId(id);
	}
}
