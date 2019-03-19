package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.entities.Administrator;

@Local
public interface IAdminService {
	public Administrator isExist(Administrator adIn);

}
