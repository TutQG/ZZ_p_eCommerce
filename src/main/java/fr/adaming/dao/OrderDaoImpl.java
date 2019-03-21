package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Order;
import fr.adaming.entities.Product;

@Repository
public class OrderDaoImpl implements IOrderDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Order> getAllOrder(Order ord) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();

		String req = "SELECT ord FROM Order Ord ";
		// recup le query
		Query query = s.createQuery(req);
		// passage des params
		query.setParameter("pId", ord.getId());

		List<Order> ordListe = query.list();

		return ordListe;
	}

	@Override
	public Order addOrder(Order ord) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		s.save(ord);

		return ord;
	}

	@Override
	public int delOrder(Order ord) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		String req = "DELETE Order as ord WHERE ord.id=pIdOrd";

		Query query = s.createQuery(req);

		query.setParameter("pIdOrd", ord.getId());

		return query.executeUpdate();
	}

}
