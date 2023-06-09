package com.acquistionline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acquistionline.model.Order;

@Repository
public interface InterfaceOrderRepo extends CrudRepository<Order, Integer> {

	public Iterable<Order> findByClientCode(String id);
	
	@Query("delete from Order o where o.client = ?1")
	public void deleteByClientId(String id);
}
