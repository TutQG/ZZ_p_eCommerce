package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.entities.OrderLine;
import fr.adaming.entities.Product;

@Stateless
public class OLDaoImpl implements IOLDao {

	@PersistenceContext(unitName = "PU_ECommerce")
	private EntityManager em;

	@Override
	public List<OrderLine> getAllOL(OrderLine ol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderLine addOL(OrderLine ol) {
		em.persist(ol);
		return ol;
	}

	@Override
	public int updateOL(OrderLine ol) {
		String req = "UPDATE OrderLine as ol SET ol.quatity=:pQuantity, ol.price=:pPrice WHERE ol.id=pIdOL";
		Query query = em.createQuery(req);

		query.setParameter("pIdOL", ol.getId());
		query.setParameter("pQuantity", ol.getQuatity());
		query.setParameter("pPrice", ol.getPrice());

		return query.executeUpdate();
	}

	@Override
	public int deleteOL(OrderLine ol) {
		String req = "DELETE OrderLine as ol WHERE ol.id=:pIdOL";
		Query query = em.createQuery(req);

		query.setParameter("pIdOL", ol.getId());

		return query.executeUpdate();
	}

	@Override
	public double price(OrderLine ol, Product pdt) {

		return 0;
	}

}
