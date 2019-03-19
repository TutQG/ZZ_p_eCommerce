package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.Category;

public interface ICategoryService {
	public List<Category> getAllCat(Category cat);

	public Category addCat(Category cat);

	public int updateCat(Category cat);

	public int deleteCat(Category cat);

	public Category getCatById(Category cat);
}
