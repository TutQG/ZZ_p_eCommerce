package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.entities.Order;

@Stateless
public class OrderDaoImpl implements IOrderDao {

	@PersistenceContext(unitName = "PU_ECommerce")
	private EntityManager em;

	@Override
	public List<Order> getAllOrder(Order ord) {
		String req = "SELECT ord FROM Order Ord ";
		Query query = em.createQuery(req);

		List<Order> listOrder = query.getResultList();

		return listOrder;
	}

	@Override
	public Order addOrder(Order ord) {
		em.persist(ord);

		return ord;
	}

	@Override
	public int delOrder(Order ord) {
		String req = "DELETE Order as ord WHERE ord.id=pIdOrd";
		Query query = em.createQuery(req);

		query.setParameter("pIdOrd", ord.getId());

		return query.executeUpdate();
	}

}
