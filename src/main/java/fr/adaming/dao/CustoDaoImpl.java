package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.entities.Customer;

@Stateless
public class CustoDaoImpl implements ICustoDao {

	@PersistenceContext(unitName = "PU_ECommerce")
	private EntityManager em;

	@Override
	public Customer isExist(Customer cuIn) {
		String req = "SELECT cu FROM Customer as cu WHERE cu.mail=:pMail AND cu.pwd=:pPwd";
		// recup un objet de type query
		Query query = em.createQuery(req);

		// passage params
		query.setParameter("pMail", cuIn.getMail());
		query.setParameter("pPwd", cuIn.getPwd());

		try {
			return (Customer) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public Customer addCustomer(Customer cuIn) {
		em.persist(cuIn);
		return cuIn;
	}
}