package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Administrator;

@Stateful

public class AdminServiceImpl implements IAdminService {

	// uml en java
	@EJB
	private IAdminDao adDao;

	@Override
	public Administrator isExist(Administrator adIn) {
		// TODO Auto-generated method stub
		return adDao.isExist(adIn);
	}

}
