package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.entities.Customer;

@Local
public interface ICustoService {
	public Customer isExist(Customer cuIn);
	
	public Customer addCustomer(Customer cuIn);
}
