package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.entities.Order;

@Local
public interface IOrderService {

	public List<Order> getAllOrder(Order ord);

	public Order addOrder(Order ord);

	public int delOrder(Order ord);

}
