package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Category;

@Local
public interface ICategoryDao {

	public List<Category> getAllCat(Category cat);

	public Category addCat(Category cat);

	public int updateCat(Category cat);

	public int deleteCat(Category cat);

	public Category getCatById(Category cat);

}
