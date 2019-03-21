package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICustoDao;
import fr.adaming.entities.Customer;

@Service("custoService")
@Transactional // peut etre utilisé sur la classe (toutes les méthodes sont
				// transactionelles) ou sur une methode en particuliere
public class CustoServiceImpl implements ICustoService {

	// transformation de l'association uml en java
	@Autowired
	private ICustoDao custoDao;

	public void setCustoDao(ICustoDao custoDao) {
		this.custoDao = custoDao;
	}

	@Override
	public Customer isExist(Customer cuIn) {
		// TODO Auto-generated method stub
		return custoDao.isExist(cuIn);
	}

	@Override
	public Customer addCustomer(Customer cuIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
