package fr.adaming.service;

import java.util.List;

import fr.adaming.entities.OrderLine;

public interface IOLService {

	public List<OrderLine> getAllOL(OrderLine ol);

	public OrderLine addOL(OrderLine ol);

	public int updateOL(OrderLine ol);

	public int deleteOL(OrderLine ol);

}
