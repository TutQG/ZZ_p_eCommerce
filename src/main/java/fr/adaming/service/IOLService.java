package fr.adaming.service;

import java.util.List;

import javax.ejb.Stateless;

import fr.adaming.entities.OrderLine;

@Stateless
public interface IOLService {

	public List<OrderLine> getAllOL(OrderLine ol);

	public OrderLine addOL(OrderLine ol);

	public int updateOL(OrderLine ol);

	public int deleteOL(OrderLine ol);

}
