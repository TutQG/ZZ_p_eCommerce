package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Customer;

@Repository
public class CustoDaoImpl implements ICustoDao {


	
	@Autowired
	private SessionFactory sf;
	
	//le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Customer isExist(Customer cuIn) {
		//recup le bus (session de hibernate)
		Session s=sf.getCurrentSession();
		
		//req  hql
		String req="FROM Customers as c WHERE c.mail=:pMail AND c.pwd=:pPwd";
		
		//recup le query
		Query query=s.createQuery(req);
		
		//passage des params
		query.setParameter("pMail", cuIn.getMail());
		query.setParameter("pPwd", cuIn.getPwd());
		
		
		return (Customer) query.uniqueResult();
	}

	@Override
	public Customer addCustomer(Customer cuIn) {
		// TODO Auto-generated method stub
		return null;
	}

}