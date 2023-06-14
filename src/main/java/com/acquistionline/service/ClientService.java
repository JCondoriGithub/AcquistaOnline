package com.acquistionline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acquistionline.model.Client;
import com.acquistionline.model.Order;
import com.acquistionline.model.Product;
import com.acquistionline.repository.InterfaceClientRepo;
import com.acquistionline.repository.InterfaceOrderRepo;
import com.acquistionline.repository.InterfaceProductRepo;

@Service
public class ClientService implements InterfaceClientService {
	
	@Autowired
	private InterfaceClientRepo clientRepo;
	
	@Autowired
	private InterfaceProductRepo productRepo;
	
	@Autowired
	private InterfaceOrderRepo orderRepo;

	public ClientService() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Iterable<Client> getAll() {
		return clientRepo.findAll();
	}

	@Override
	public Optional<Client> getById(String id) {
		return clientRepo.findById(id);
	}

	@Override
	public Client create(Client client) {
		return clientRepo.save(client);
	}

	@Override
	public Optional<Client> update(String id, Client client) {

		Optional<Client> foundClient = clientRepo.findById(id);
		
		if(foundClient.isEmpty())
			return Optional.empty();
		
		if(client.getCode() != null)
			foundClient.get().setCode(client.getCode());
					
		if(client.getName() != null)
			foundClient.get().setName(client.getName());
		
		if(client.getSurname() != null)
			foundClient.get().setSurname(client.getSurname());
		
		if(client.getEmail() != null)
			foundClient.get().setEmail(client.getEmail());
		
		clientRepo.save(foundClient.get());
		
		return foundClient;
	}

	@Override
	public boolean delete(String id) {

		Optional<Client> foundClient = clientRepo.findById(id);
		
		if(foundClient.isEmpty())
			return false;
		
		Iterable<Order> orders = orderRepo.findByClientCode(id);
		
		for(Order o: orders) {
			
			Product p = o.getProduct();
			p.setQtyAvailable(p.getQtyAvailable() + o.getQtyProduct());
			productRepo.save(p);
			
			orderRepo.delete(o);
		}
		
		clientRepo.delete(foundClient.get());
		return true;
	}

}
