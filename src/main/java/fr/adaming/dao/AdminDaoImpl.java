package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.entities.Administrator;

@Stateless
public class AdminDaoImpl implements IAdminDao {

	@PersistenceContext(unitName = "PU_ECommerce")
	private EntityManager em;

	@Override
	public Administrator isExist(Administrator adIn) {

		String req = "SELECT ad FROM Administrator as ad WHERE ad.mail=:pMail AND ad.pwd=:pPwd";
		// recup un objet de type query
		Query query = em.createQuery(req);

		// passage params
		query.setParameter("pMail", adIn.getMail());
		query.setParameter("pPwd", adIn.getPwd());

		try {
			return (Administrator) query.getSingleResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
}