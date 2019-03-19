package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

public class AdminManagedBean {

	// uml en java
	@EJB
	private IAdminService adService;
	@EJB
	private ICategoryService catService;
	@EJB
	private IProductService pdtService;

	private Administrator admin;

	private HttpSession mySession;
	
	private UploadedFile photo;
	
	private UploadedFile picture;

	private Category cat;

	private Product pdt;

	// CONSTRUCTEUR VIDE
	public AdminManagedBean() {
		this.admin = new Administrator();
		this.cat = new Category();
		this.pdt = new Product();
	}

	@PostConstruct // cette annotation sert a exucutela methode apres
	// l'inst,ciation de l'objet
	public void init() {
		mySession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
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

	public String addCat() {
		

		if(this.photo!=null){
			this.cat.setPhoto(this.photo.getContents());
		}
		Category catOut = catService.addCat(cat);
		
		if (catOut.getId() != 0) {
			List<Category> listCat = catService.getAllCat(cat);

			mySession.setAttribute("lCatSession", listCat);

			return "catDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Category not created"));

			return "addCat";
		}
	}

	public String updateCat() {
		int catUp = catService.updateCat(cat);
		if (catUp != 0) {
			List<Category> listCat = catService.getAllCat(cat);

			mySession.setAttribute("lCatSession", listCat);
			return "catDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update failed"));
			return "updateCat";
		}

	}

	public String delCat() {
		int catDel = catService.deleteCat(cat);

		if (catDel != 0) {
			List<Category> listCat = catService.getAllCat(cat);

			mySession.setAttribute("lCatSession", listCat);
			return "catDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete failed"));
			return "delCat";
		}
	}

	public String addPdt() {
		

		if(this.picture!=null){
			this.pdt.setPicture(this.picture.getContents());
		}
		Product pdtOut = pdtService.addPdt(pdt);
		
		if (pdtOut != null) {
			List<Product> listPdt = pdtService.getAllPdt(pdt);
			mySession.setAttribute("lPdtSession", listPdt);
			return "pdtDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product not created"));
			return "addPdt";
		}
	}

	public String updatePdt() {
		int pdtUp = pdtService.updatePdt(pdt);

		if (pdtUp != 0) {
			List<Product> listPdt = pdtService.getAllPdt(pdt);
			mySession.setAttribute("lPdtSession", listPdt);
			return "pdtDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Update failed"));
			return "updatePdt";
		}
	}

	public String delPdt() {
		int pdtDel = pdtService.delPdt(pdt);

		if (pdtDel != 0) {
			List<Product> listPdt = pdtService.getAllPdt(pdt);
			mySession.setAttribute("lPdtSession", listPdt);
			return "pdtDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Delete failed"));
			return "delPdt";
		}
	}
}
