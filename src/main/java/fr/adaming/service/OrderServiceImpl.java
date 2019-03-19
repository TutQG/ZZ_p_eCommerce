package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IOrderDao;
import fr.adaming.entities.Order;

@Stateful
public class OrderServiceImpl implements IOrderService {

	@EJB
	IOrderDao ordDao;

	@Override
	public List<Order> getAllOrder(Order ord) {
		// TODO Auto-generated method stub
		return ordDao.getAllOrder(ord);
	}

	@Override
	public Order addOrder(Order ord) {
		// TODO Auto-generated method stub
		return ordDao.addOrder(ord);
	}

	@Override
	public int delOrder(Order ord) {
		// TODO Auto-generated method stub
		return ordDao.delOrder(ord);
	}

}
