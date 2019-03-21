package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Product;

@Repository
public class ProductDaoImpl implements IProductDao {

	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Product> getAllPdt(Product pdt) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();

		// req hql
		String req = "SELECT pdt From Product pdt";
		
		// recup le query
		Query query = s.createQuery(req);
		// passage des params
		query.setParameter("pId", pdt.getId());

		List<Product> pdtListe = query.list();

		return pdtListe;
	}

	@Override
	public Product addPdt(Product pdt) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		s.save(pdt);

		return pdt;
	}

	@Override
	public int updatePdt(Product pdt) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		String req = "UPDATE Product as pdt SET pdt.designation=:pDesign, pdt.description=:pDescr, pdt.price=:pPrice,"
				+ " pdt.quantity=:pQuantity, pdt.picture=:pPicture WHERE id=:pIdPdt";
		
		Query query=s.createQuery(req);
		
		// passage des params
		query.setParameter("pDesign", pdt.getDesignation());
		query.setParameter("pDescr", pdt.getDescription());
		query.setParameter("pPrice", pdt.getPrice());
		query.setParameter("pQuantity", pdt.getQuantity());
		query.setParameter("pPicture", pdt.getPicture());
		query.setParameter("pIdPdt", pdt.getId());

		return query.executeUpdate();
	}

	@Override
	public int delPdt(Product pdt) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		String req = "DELETE Product as pdt WHERE id=:pIdPdt";
		
		Query query=s.createQuery(req);
		
		query.setParameter("pIdPdt", pdt.getId());
		
		return query.executeUpdate();
	}

	@Override
	public Product getPdtById(Product pdt) {
	// recup le bus (session de hibernate)
			Session s = sf.getCurrentSession();
			
		Product pdtFind=(Product) s.get(Product.class, pdt.getId());
		
		return pdtFind;
	}
}
