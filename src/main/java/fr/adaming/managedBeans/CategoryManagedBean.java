package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.entities.Administrator;
import fr.adaming.entities.Category;
import fr.adaming.entities.Product;
import fr.adaming.service.ICategoryService;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategoryManagedBean implements Serializable{
	
	// uml en java
	private ICategoryService catService;
	
	private Category category;
	private Administrator admin;
	private Product produit;
	
	private HttpSession mySession;
	
	public CategoryManagedBean() {
		this.category = new Category();
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String addCat() {
		Category catAjout = catService.addCat(category);

		if (catAjout.getId() != 0) {
			// récupérer la nouvelle liste
			// La liste des étu de ce formateur
			List<Category> catListe = catService.getAllCat(category);

			// Mettre la liste dans la session
			mySession.setAttribute("lSession", catListe);

			return "homeAdmin";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "addCat";
		}
	}
	
	public String updateCat() {

		int catModif = catService.updateCat(category);

		if (catModif != 0) {
			List<Category> liste = catService.getAllCat(category);

			mySession.setAttribute("lSession", liste);

			return "homeAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a échoué"));
			return "updateCat";
		}

	}

	public String deleteCat(){
		int catModif =catService.deleteCat(category);
				
				if (catModif !=0){
					
					List<Category> catListe=catService.getAllCat(category);
					
					mySession.setAttribute("lSession", catListe);
					
					return "homeAdmin";
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
					return "delCat";
				}
			}
	
}
