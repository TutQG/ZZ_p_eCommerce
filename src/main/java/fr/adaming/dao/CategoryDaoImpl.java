package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Category;
import fr.adaming.entities.Etudiant;

@Repository
public class CategoryDaoImpl implements ICategoryDao {

	
	@Autowired
	private SessionFactory sf;
	
	@Override
	public List<Category> getAllCat(Category cat) {

		// recup le bus (session de hibernate)
				Session s = sf.getCurrentSession();

				// req hql
				String req = "SELECT cat From Category cat";
				
				// recup le query
				Query query = s.createQuery(req);
				// passage des params
				query.setParameter("pId", cat.getId());

				List<Category> catListe = query.list();

				return catListe;
	}

	@Override
	public Category addCat(Category cat) {
		// recup le bus (session de hibernate)
				Session s = sf.getCurrentSession();
				s.save(cat);

				return cat;
	}

	@Override
	public int updateCat(Category cat) {
		// recup le bus (session de hibernate)
				Session s = sf.getCurrentSession();
				
				String req = "UPDATE Category as cat SET cat.name=:pName, cat.photo=:pPhoto, cat.description=:pDescription WHERE cat.id=:pIdCat ";
				
				Query query=s.createQuery(req);
				
				// passage des params
				query.setParameter("pName", cat.getName());
				query.setParameter("pPhoto", cat.getPhoto());
				query.setParameter("pDescription", cat.getDescription());
				query.setParameter("pIdCat", cat.getId());
				
				return query.executeUpdate();
	}

	@Override
	public int deleteCat(Category cat) {
		// recup le bus (session de hibernate)
				Session s = sf.getCurrentSession();
				
				String req = "DELETE Category as cat WHERE cat.id=:pIdCat";
				
				Query query=s.createQuery(req);
				
				query.setParameter("pIdCat", cat.getId());
				
				return query.executeUpdate();
			}

	@Override
	public Category getCatById(Category cat) {
		// recup le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		Category catFind=(Category)s.get(Category.class, cat.getId());
		return catFind;
	}

}
