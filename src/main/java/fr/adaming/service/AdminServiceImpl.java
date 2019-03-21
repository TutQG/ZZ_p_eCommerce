package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Administrator;
@Service("adService")
@Transactional//peut etre utilisé sur la classe (toutes les méthodes sont transactionelles) ou sur une methode en particuliere

public class AdminServiceImpl implements IAdminService {

	//transformation de l'association uml en java
		@Autowired
		private IAdminDao amdinDao;
		
		//setter pour l'injection dépendance
		public void setAmdinDao(IAdminDao amdinDao) {
			this.amdinDao = amdinDao;
		}



	@Override
	public Administrator isExist(Administrator adIn) {
		// TODO Auto-generated method stub
		return amdinDao.isExist(adIn);
	}

}
