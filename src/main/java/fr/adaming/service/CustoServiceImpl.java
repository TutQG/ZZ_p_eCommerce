package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICustoDao;
import fr.adaming.entities.Customer;

@Stateful
public class CustoServiceImpl implements ICustoService {

	// uml en java
	@EJB
	private ICustoDao cuDao;

	@Override
	public Customer isExist(Customer cuIn) {

		return cuDao.isExist(cuIn);
	}

	@Override
	public Customer addCustomer(Customer cuIn) {
		
		return cuDao.addCustomer(cuIn);
	}

	
	}

