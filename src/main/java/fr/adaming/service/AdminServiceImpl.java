package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.entities.Administrator;
@Service("adService")
@Transactional//peut etre utilis� sur la classe (toutes les m�thodes sont transactionelles) ou sur une methode en particuliere

public class AdminServiceImpl implements IAdminService {

	//transformation de l'association uml en java
		@Autowired
		private IAdminDao amdinDao;
		
		//setter pour l'injection d�pendance
		public void setAmdinDao(IAdminDao amdinDao) {
			this.amdinDao = amdinDao;
		}



	@Override
	public Administrator isExist(Administrator adIn) {
		// TODO Auto-generated method stub
		return amdinDao.isExist(adIn);
	}

}
