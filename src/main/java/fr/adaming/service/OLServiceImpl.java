package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IOLDao;
import fr.adaming.entities.OrderLine;

@Stateful
public class OLServiceImpl implements IOLService {
	@EJB
	IOLDao olDao;

	@Override
	public List<OrderLine> getAllOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return olDao.getAllOL(ol);
	}

	@Override
	public OrderLine addOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return olDao.addOL(ol);
	}

	@Override
	public int updateOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return olDao.updateOL(ol);
	}

	@Override
	public int deleteOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return olDao.deleteOL(ol);
	}

}
