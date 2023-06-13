package com.acquistionline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acquistionline.model.Order;

@Repository
public interface InterfaceOrderRepo extends CrudRepository<Order, Integer> {

	public Iterable<Order> findByClientCode(String id);
	
	public Iterable<Order> findByProductId(String id);
	
	@Query("select sum(o.product.price * o.qtyProduct) from Order o where o.client.code = ?1")
	public double totPriceByClientId(String id);
}
