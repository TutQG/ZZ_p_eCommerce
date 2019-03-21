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

	@PostConstruct // Cette annotation sert � dire que la m�thode doit �tre
	// �x�cut�e apr�s l'instanciation de l'objet
	public void init() {

		mySession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.admin = (Administrator) mySession.getAttribute("adSession");

	}

	// le setter est obligatoire pour l'injection de d�pendance via
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

		messageMail = "Bonjour, \n Nous vous informons que nous avons ajout� un nouveau produit \n Cordialement, \n Guillaume et Arthur";

		int verifMail = 0;
		
		if (pdtAjout.getId() != 0) {
			

			SendMailSSL sm = new SendMailSSL();
			try {
				// V�rif va servir � savoir si le mail est envoy� vu que la
				// fonction sendmail retourne un int
				verifMail = sm.sendMail("arthur.quillent@gmail.com", messageMail);

			} catch (Exception e) {

				e.printStackTrace();
			}

			if (verifMail != 0) {
				// r�cup�rer la nouvelle liste
				// La liste des �tu de ce formateur
				List<Product> pdtListe = pdtService.getAllPdt(pdt);

				// Mettre la liste dans la session
				mySession.setAttribute("lSession", pdtListe);
				return "homeAdmin";

			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit ajout� mais mail non envoy�"));
				return "homeAdmin";
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�"));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a �chou�"));
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La suppression a �chou�"));
			return "delPdt";
		}
	}

}
