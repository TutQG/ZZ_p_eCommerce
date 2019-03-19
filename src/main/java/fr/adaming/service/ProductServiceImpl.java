package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProductDao;
import fr.adaming.entities.Product;

@Stateful

public class ProductServiceImpl implements IProductService {
	@EJB
	IProductDao pdtDao;

	@Override
	public List<Product> getAllPdt(Product pdt) {
		// TODO Auto-generated method stub
		return pdtDao.getAllPdt(pdt);
	}

	@Override
	public Product addPdt(Product pdt) {
		// TODO Auto-generated method stub
		return pdtDao.addPdt(pdt);
	}

	@Override
	public int updatePdt(Product pdt) {
		// TODO Auto-generated method stub
		return pdtDao.updatePdt(pdt);
	}

	@Override
	public int delPdt(Product pdt) {
		// TODO Auto-generated method stub
		return pdtDao.delPdt(pdt);
	}

	@Override
	public Product getPdtById(Product pdt) {
		// TODO Auto-generated method stub
		return null;
	}

}
