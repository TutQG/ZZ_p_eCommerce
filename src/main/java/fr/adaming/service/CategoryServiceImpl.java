package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategoryDao;
import fr.adaming.entities.Category;

@Service("catService")
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	// transformer l'asso uml en java

	@Autowired
	private ICategoryDao catDao;

	public void setCatDao(ICategoryDao catDao) {
		this.catDao = catDao;
	}

	@Override
	public List<Category> getAllCat(Category cat) {
		// TODO Auto-generated method stub
		return catDao.getAllCat(cat);
	}

	@Override
	public Category addCat(Category cat) {
		
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
		return catDao.getCatById(cat);
	}

}
