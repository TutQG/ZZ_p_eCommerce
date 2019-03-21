package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProductDao;
import fr.adaming.entities.Product;

@Service("pdtService")
@Transactional
public class ProductServiceImpl implements IProductService {

	// transformer l'asso uml en java

		@Autowired
		private IProductDao pdtDao;
		

		public void setPdtDao(IProductDao pdtDao) {
			this.pdtDao = pdtDao;
		}

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
		return pdtDao.getPdtById(pdt);
	}

}
