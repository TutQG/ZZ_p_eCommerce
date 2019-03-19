package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Product;

@Local

public interface IProductService {
	public List<Product> getAllPdt(Product pdt);

	public Product addPdt(Product pdt);

	public int updatePdt(Product pdt);

	public int delPdt(Product pdt);

	public Product getPdtById(Product pdt);

}
