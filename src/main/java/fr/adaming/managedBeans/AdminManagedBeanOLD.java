package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.UploadedFile;

import fr.adaming.entities.Administrator;
import fr.adaming.entities.Category;
import fr.adaming.entities.Product;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategoryService;
import fr.adaming.service.IProductService;

@ManagedBean(name = "adMB")
@RequestScoped

public class AdminManagedBeanOLD implements Serializable {

	// uml en java
	@ManagedProperty(value = "#{adService}")
	private IAdminService adService;
	@ManagedProperty(value = "#{catService}")
	private ICategoryService catService;
	@ManagedProperty(value = "#{pdtService}")
	private IProductService pdtService;

	private Administrator admin;

	private HttpSession mySession;

	private UploadedFile photo;

	private UploadedFile picture;

	private Category cat;

	private Product pdt;

	// CONSTRUCTEUR VIDE
	public AdminManagedBeanOLD() {
		this.admin=new Administrator();
	}

	// le setter est obligatoire pour l'injection de dépendance via
	// @ManagedProperty
	
	public void setAdService(IAdminService adService) {
		this.adService = adService;
	}

	public void setCatService(ICategoryService catService) {
		this.catService = catService;
	}

	public void setPdtService(IProductService pdtService) {
		this.pdtService = pdtService;
	}

	public IAdminService getAdService() {
		return adService;
	}

	public ICategoryService getCatService() {
		return catService;
	}

	public IProductService getPdtService() {
		return pdtService;
	}

	// getters & setters
	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public Product getPdt() {
		return pdt;
	}

	public void setPdt(Product pdt) {
		this.pdt = pdt;
	}

	public UploadedFile getPhoto() {
		return photo;
	}

	public void setPhoto(UploadedFile photo) {
		this.photo = photo;
	}

	public UploadedFile getPicture() {
		return picture;
	}

	public void setPicture(UploadedFile picture) {
		this.picture = picture;
	}

	// methode métier
	public String connect() {
		Administrator adOut = adService.isExist(admin);

		if (adOut != null) {
			// recup list
			List<Category> listCat = catService.getAllCat(cat);
			List<Product> listPdt = pdtService.getAllPdt(pdt);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lCatSession", listCat);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lPdtSession", listPdt);
			FacesContext.getCurrentInstance().getExternalContext().getSession(false);

			return "homeAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please enter correct Mail or Password"));
			return "login";
		}
	}

}
