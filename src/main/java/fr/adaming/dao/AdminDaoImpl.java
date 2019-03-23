package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.entities.Administrator;


@Repository
public class AdminDaoImpl implements IAdminDao {

	
	@Autowired
	private SessionFactory sf;
	
	//le setter pour l'injection de dépendance
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Administrator isExist(Administrator adIn) {
		//recup le bus (session de hibernate)
				Session s=sf.getCurrentSession();
				
				//req  hql
				String req="FROM Administrator as a WHERE a.mail=:pMail AND a.pwd=:pPwd";
				
				//recup le query
				Query query=s.createQuery(req);
				
				//passage des params
				query.setParameter("pMail", adIn.getMail());
				query.setParameter("pPwd", adIn.getPwd());
				
				
				return (Administrator) query.uniqueResult();
			}


}