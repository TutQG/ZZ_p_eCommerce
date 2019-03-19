package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.entities.Customer;

@Local
public interface ICustoDao {
	public Customer isExist(Customer cuIn);
	
	public Customer addCustomer(Customer cuIn);
}
