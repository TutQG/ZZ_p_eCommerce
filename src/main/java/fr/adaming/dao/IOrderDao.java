package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.Order;

public interface IOrderDao {

	public List<Order> getAllOrder(Order ord);

	public Order addOrder(Order ord);

	public int delOrder(Order ord);

}
