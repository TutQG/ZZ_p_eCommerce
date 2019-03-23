package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.entities.Administrator;
import fr.adaming.entities.Category;
import fr.adaming.entities.Product;
import fr.adaming.service.ICategoryService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategoryManagedBean implements Serializable{
	
	// uml en java
	@ManagedProperty(value = "#{catService}")
	private ICategoryService catService;
	
	private Category cat;
	private Administrator admin;
	private Product pdt;
	private UploadedFile image;
	
	private HttpSession mySession;
	
	public CategoryManagedBean() {
		this.cat = new Category();
	}
	
	@Autowired
	private SessionFactory sf;

	@PostConstruct // Cette annotation sert à dire que la méthode doit être
	// éxécutée après l'instanciation de l'objet
	public void init() {

		mySession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Administrator) mySession.getAttribute("adSession");

	}

	public void setCatService(ICategoryService catService) {
		this.catService = catService;
	}


	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	public Category getCategory() {
		return cat;
	}

	public void setCategory(Category category) {
		this.cat = category;
	}
	
	
	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public String addCat() {
		
		if(this.image!=null){
			this.cat.setPhoto(this.image.getContents());
		}
		
		Category catAjout = catService.addCat(cat);

		if (catAjout.getId() != 0) {
			// récupérer la nouvelle liste
			// La liste des étu de ce formateur
			List<Category> catListe = catService.getAllCat(cat);

			// Mettre la liste dans la session
			mySession.setAttribute("lCatSession", catListe);

			return "catDisplay";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "addCat";
		}
	}
	
	public String updateCat() {

		int catModif = catService.updateCat(cat);

		if (catModif != 0) {
			List<Category> liste = catService.getAllCat(cat);

			mySession.setAttribute("lCatSession", liste);

			return "catDisplay";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a échoué"));
			return "updateCat";
		}

	}

	public String deleteCat(){
		int catModif =catService.deleteCat(cat);
				
				if (catModif !=0){
					
					List<Category> catListe=catService.getAllCat(cat);
					
					mySession.setAttribute("lCatSession", catListe);
					
					return "catDisplay";
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
					return "delCat";
				}
			}
	
}
