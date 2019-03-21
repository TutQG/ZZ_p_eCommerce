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
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.entities.Administrator;
import fr.adaming.entities.Category;
import fr.adaming.entities.Product;
import fr.adaming.entities.SendMailSSL;
import fr.adaming.service.IProductService;

@ManagedBean(name = "pdtMB")
@RequestScoped
public class ProductManagedBean implements Serializable {

	// uml en java
	@ManagedProperty(value = "#{pdtService}")
	private IProductService pdtService;

	private Product pdt;

	private Administrator admin;

	private Category cat;

	private HttpSession mySession;

	private Product product;

	public ProductManagedBean() {
		this.product = new Product();
	}

	@Autowired
	private SessionFactory sf;

	@PostConstruct // Cette annotation sert à dire que la méthode doit être
	// éxécutée après l'instanciation de l'objet
	public void init() {

		mySession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Administrator) mySession.getAttribute("adSession");

	}

	// le setter est obligatoire pour l'injection de dépendance via
	// @ManagedProperty
	public void setPdtService(IProductService pdtService) {
		this.pdtService = pdtService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String addPdt() {
		Product pdtAjout = pdtService.addPdt(product);

		
		String messageMail;

		messageMail = "Bonjour, \n Nous vous informons que nous avons ajouté un nouveau produit \n Cordialement, \n Guillaume et Arthur";

		int verifMail = 0;
		
		if (pdtAjout.getId() != 0) {
			

			SendMailSSL sm = new SendMailSSL();
			try {
				// Vérif va servir à savoir si le mail est envoyé vu que la
				// fonction sendmail retourne un int
				verifMail = sm.sendMail("arthur.quillent@gmail.com", messageMail);

			} catch (Exception e) {

				e.printStackTrace();
			}

			if (verifMail != 0) {
				// récupérer la nouvelle liste
				// La liste des étu de ce formateur
				List<Product> pdtListe = pdtService.getAllPdt(pdt);

				// Mettre la liste dans la session
				mySession.setAttribute("lSession", pdtListe);
				return "homeAdmin";

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit ajouté mais mail non envoyé"));
				return "homeAdmin";
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "addPdt";
		}
	}

	public String updatePdt() {

		int pdtModif = pdtService.updatePdt(pdt);

		if (pdtModif != 0) {
			List<Product> liste = pdtService.getAllPdt(pdt);

			mySession.setAttribute("lSession", liste);

			return "homeAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a échoué"));
			return "updatePdt";
		}

	}

	public String deletePdt() {
		int pdtModif = pdtService.delPdt(pdt);

		if (pdtModif != 0) {

			List<Product> pdtListe = pdtService.getAllPdt(pdt);

			mySession.setAttribute("lSession", pdtListe);

			return "homeAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a échoué"));
			return "delPdt";
		}
	}

}
