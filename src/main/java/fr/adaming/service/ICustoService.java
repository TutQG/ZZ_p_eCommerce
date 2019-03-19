package fr.adaming.service;

import fr.adaming.entities.Customer;

public interface ICustoService {
	public Customer isExist(Customer cuIn);

	public Customer addCustomer(Customer cuIn);
}
