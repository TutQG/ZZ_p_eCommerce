package fr.adaming.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.entities.Adress;
import fr.adaming.entities.Cart;
import fr.adaming.entities.Customer;
import fr.adaming.entities.Order;
import fr.adaming.entities.OrderLine;
import fr.adaming.entities.Product;
import fr.adaming.entities.SendMailSSL;
import fr.adaming.service.IOLService;
import fr.adaming.service.IOrderService;
import fr.adaming.service.IProductService;

public class CartManagedBean {

	IOLService olService;

	IProductService pdtService;

	IOrderService orderService;

	// attribut
	private Customer custo;
	private Cart cart;
	private OrderLine ol;
	private Product pdt;
	private Adress adress;
	private int quantite;
	private double prix;
	private double prixT;
	private int taille;
	private double total;
	private String messageMail;
	private List<OrderLine> listeLico;
	private List<Product> listePdt;
	private String promo;

	private HttpSession mySession;

	public CartManagedBean() {
		this.custo = new Customer();
		this.adress = new Adress();
		this.cart = new Cart();
		this.pdt = new Product();
		this.listeLico = new ArrayList<OrderLine>();
		this.ol = new OrderLine();
		this.total = 0;
		this.taille = listeLico.size();
		cart.setListelico(listeLico);

	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getPdt() {
		return pdt;
	}

	public void setPdt(Product pdt) {
		this.pdt = pdt;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public List<OrderLine> getListeLico() {
		return listeLico;
	}

	public void setListeLico(List<OrderLine> listeLico) {
		this.listeLico = listeLico;
	}

	public Customer getCusto() {
		return custo;
	}

	public void setCusto(Customer custo) {
		this.custo = custo;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrixT() {
		return prixT;
	}

	public void setPrixT(double prixT) {
		this.prixT = prixT;
	}

	public List<Product> getListePdt() {
		return listePdt;
	}

	public void setListePdt(List<Product> listePdt) {
		this.listePdt = listePdt;
	}

	public IOLService getOlService() {
		return olService;
	}

	public void setOlService(IOLService olService) {
		this.olService = olService;
	}

	public IProductService getPdtService() {
		return pdtService;
	}

	public void setPdtService(IProductService pdtService) {
		this.pdtService = pdtService;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public OrderLine getOl() {
		return ol;
	}

	public void setOl(OrderLine ol) {
		this.ol = ol;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMessageMail() {
		return messageMail;
	}

	public void setMessageMail(String messageMail) {
		this.messageMail = messageMail;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public HttpSession getMySession() {
		return mySession;
	}

	public void setMySession(HttpSession mySession) {
		this.mySession = mySession;
	}

	@PostConstruct // Cette annotation sert à dire que la méthode doit être
					// exécutée après l'instanciation de l'objet
	public void init() {
		mySession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		mySession.setAttribute("lsession", listeLico);
		mySession.setAttribute("taille", taille);
		mySession.setAttribute("total", total);
	}

	// Les méthodes métier

	public String ajoutProPanier() {

		// Créer une nouvelle ligne de Commande
		OrderLine lcOut = new OrderLine();
		lcOut.setProduct(pdt);

		// Vérifier le stock
		if (quantite <= pdt.getQuantity()) {
			lcOut.setQuatity(quantite);
			this.prixT = pdt.getPrice() * this.quantite;
			lcOut.setPrice(prixT);

			// Modifier la quantité du produit dans la database
			pdt.setQuantity(pdt.getQuantity() - quantite);
			pdtService.updatePdt(pdt);

			// Ajouter la ligne de Commande à la liste
			this.listeLico.add(lcOut);
			this.taille = listeLico.size();
			this.total = this.total + lcOut.getPrice();

			// Appel de la méthode pour créer la ligne de co dans la database
			lcOut = olService.addOL(pdt, quantite);

			// Mettre à jour la liste dans la session
			mySession.setAttribute("lsession", listeLico);
			mySession.setAttribute("taille", taille);
			mySession.setAttribute("total", total);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a été ajouté au panier"));

			return "panier";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Il n'y a pas assez de produit en stock"));
			return "testclient";
		}

	}

	public String supprProPanier() {
		// appel de la méthode service pour supprimer un produit du panier
		// (suppr la ligne de co)
		int verif = olService.deleteOL(pdt);
		if (verif != 0) {

			// On cherche la ligne du panier où se trouve le produit qu'on veut
			// supprimer
			for (int i = 0; i < listeLico.size(); i++) {
				if (pdt.getId() == listeLico.get(i).getProduct().getId()) {
					this.total = this.total - listeLico.get(i).getPrice();
					this.listeLico.remove(i);
					this.taille = listeLico.size();
					mySession.setAttribute("taille", taille);
					mySession.setAttribute("total", total);
				}
			}

			// Mettre à jour la liste dans la session
			mySession.setAttribute("lsession", listeLico);
			mySession.setAttribute("taille", taille);
			mySession.setAttribute("total", total);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit a été supprimé du panier"));

			return "panier";
		}
		return "panier";

	}

	public String valider() {
		if (this.listeLico.isEmpty() == true) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Votre panier est vide!"));
			return "panier";
		}
		return "client";

	}

	public String validerPanier() {

		// On récupère la liste du panier
		this.listeLico = (List<OrderLine>) mySession.getAttribute("lsession");
		this.cart.setListelico(this.listeLico);

		// On enregistre la commande avec le panier, le client et son adresse
		Order comOut = orderService.enregistrerCom(cart, custo, adress);
		comOut.setListOL(listeLico);
		custo.setAdresse(adress);
		comOut.setClient(custo);

		for (int i = 0; i < listeLico.size(); i++) {
			OrderLine lcIn = this.listeLico.get(i);
			lcIn.setOrder(comOut);
		}

		// On envoie un mail

		messageMail = "Bonjour " + comOut.getCusto().getName()
				+ ", \n Nous vous informons que votre commande, faite le " + comOut.getDate() + ", a été validée."
				+ "\n Veuillez trouver ci-joint le récapitulatif de votre commande: " + "\n" + comOut.getListelico()
				+ "\n" + comOut + listeLico;

		int verifMail = 0;

		if (comOut.getId() != 0) {
			// Ici on envoie concrètement le mail en renseignant le destinataire
			// et le message
			// On oublie pas de surround la fonction pour ne pas faire planter
			// l'appli si ca crash
			SendMailSSL sm = new SendMailSSL();
			try {
				// Vérif va servir à savoir si le mail est envoyé vu que la
				// fonction sendmail retourne un int
				verifMail = sm.sendMail(custo.getMail(), messageMail);

			} catch (Exception e) {

				e.printStackTrace();
			}
			if (verifMail != 0) {
				// Si la commande est validée, on vide le panier
				for (int i = 0; i < listeLico.size(); i++) {
					this.listeLico.remove(i);
				}

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande est validée"));
				return "testclient";
			} else {
				// ajouter le message d'erreur
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Commande validée mais impossible d'envoyer le mail, vérifier l'adresse du client"));
				return "panier";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande n'a pas pu être validée"));
			return "panier";
		}
	}

	public String codePromo() {
		if (this.promo == "35XC59") {
			total = this.total - (20 * this.total / 100);
			mySession.setAttribute("total", total);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ce code promo n'est pas/plus valide!"));
		}
		return "panier";

	}

}
