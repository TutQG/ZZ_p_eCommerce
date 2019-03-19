package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategoryDao;
import fr.adaming.entities.Category;

@Stateful

public class CategoryServiceImpl implements ICategoryService {
	@EJB
	ICategoryDao catDao;

	@Override
	public List<Category> getAllCat(Category cat) {
		// TODO Auto-generated method stub
		return catDao.getAllCat(cat);
	}

	@Override
	public Category addCat(Category cat) {
		// TODO Auto-generated method stub
		return catDao.addCat(cat);
	}

	@Override
	public int updateCat(Category cat) {
		// TODO Auto-generated method stub
		return catDao.updateCat(cat);
	}

	@Override
	public int deleteCat(Category cat) {
		// TODO Auto-generated method stub
		return catDao.deleteCat(cat);
	}

	@Override
	public Category getCatById(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

}
