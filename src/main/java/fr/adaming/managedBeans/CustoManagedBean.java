package fr.adaming.managedBeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.entities.Customer;
import fr.adaming.service.ICustoService;

@ManagedBean(name = "cuMB")
@RequestScoped

public class CustoManagedBean {

	// uml en java
	@EJB
	private ICustoService cuService;

	private Customer custo;

	// CONSTRUCTEUR VIDE
	public CustoManagedBean() {
		this.custo = new Customer();
	}

	// getters & settars
	public Customer getCusto() {
		return custo;
	}

	public void setCusto(Customer custo) {
		this.custo = custo;
	}

	// methode métier
	public String connect() {
		Customer cuOut = cuService.isExist(custo);

		if (cuOut != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSession(false);

			return "homePage";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Please enter correct Mail or Password"));
			return "login";
		}
	}

	public String addCustomer() {
		System.out.println("======================");
		Customer verif = cuService.addCustomer(custo);
		if (verif != null) {

			return "homePage";

		} else {
			return "login";
		}
	}

}
