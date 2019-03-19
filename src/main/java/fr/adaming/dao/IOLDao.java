package fr.adaming.dao;

import java.util.List;

import fr.adaming.entities.OrderLine;
import fr.adaming.entities.Product;

public interface IOLDao {

	public double price(OrderLine ol, Product pdt);

	public List<OrderLine> getAllOL(OrderLine ol);

	public OrderLine addOL(OrderLine ol);

	public int updateOL(OrderLine ol);

	public int deleteOL(OrderLine ol);

}
