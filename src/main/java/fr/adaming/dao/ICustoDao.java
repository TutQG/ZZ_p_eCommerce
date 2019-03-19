package fr.adaming.dao;

import fr.adaming.entities.Customer;

public interface ICustoDao {
	public Customer isExist(Customer cuIn);

	public Customer addCustomer(Customer cuIn);
}
