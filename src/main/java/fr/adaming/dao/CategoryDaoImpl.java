package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.entities.Category;

@Stateless

public class CategoryDaoImpl implements ICategoryDao {

	@PersistenceContext(unitName = "PU_ECommerce")
	private EntityManager em;

	@Override
	public List<Category> getAllCat(Category cat) {
		String req = "SELECT cat From Category Cat";

		// recup query
		Query query = em.createQuery(req);

		List<Category> listCat=query.getResultList();
		
		for(Category category:listCat){
			category.setImg("data:image/png;base64,"+Base64.encodeBase64String(category.getPhoto()));
		}

		return listCat;
	}

	@Override
	public Category addCat(Category cat) {
		em.persist(cat);
		return cat;
	}

	@Override
	public int updateCat(Category cat) {
		String req = "UPDATE Category as cat SET cat.name=:pName, cat.photo=:pPhoto, cat.description=:pDescription WHERE cat.id=:pIdCat ";
		Query query = em.createQuery(req);

		// passage des params
		query.setParameter("pName", cat.getName());
		query.setParameter("pPhoto", cat.getPhoto());
		query.setParameter("pDescription", cat.getDescription());
		query.setParameter("pIdCat", cat.getId());

		return query.executeUpdate();
	}

	@Override
	public int deleteCat(Category cat) {
		String req = "DELETE Category as cat WHERE cat.id=:pIdCat";

		Query query = em.createQuery(req);

		query.setParameter("pIdCat", cat.getId());

		return query.executeUpdate();
	}

	@Override
	public Category getCatById(Category cat) {
		// TODO Auto-generated method stub
		return null;
	}

}
