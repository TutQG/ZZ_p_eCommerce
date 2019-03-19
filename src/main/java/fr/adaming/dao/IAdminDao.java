package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.entities.Administrator;

@Local
public interface IAdminDao {

	public Administrator isExist(Administrator adIn);

}
