package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.OrderLine;
import fr.adaming.entities.Product;

@Repository
public class OLDaoImpl implements IOLDao {
	@Autowired
	private SessionFactory sf;

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public double price(OrderLine ol, Product pdt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderLine> getAllOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderLine addOL(OrderLine ol) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		s.save(ol);
		return ol;
	}

	@Override
	public int updateOL(OrderLine ol) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();

		String req = "UPDATE OrderLine as ol SET ol.quatity=:pQuantity, ol.price=:pPrice WHERE ol.id=pIdOL";

		Query query = s.createQuery(req);

		query.setParameter("pIdOL", ol.getId());
		query.setParameter("pQuantity", ol.getQuatity());
		query.setParameter("pPrice", ol.getPrice());

		return query.executeUpdate();
	}

	@Override
	public int deleteOL(OrderLine ol) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		String req = "DELETE OrderLine as ol WHERE ol.id=:pIdOL";
		
		Query query = s.createQuery(req);
		
		query.setParameter("pIdOL", ol.getId());

		return query.executeUpdate();
	}

}
