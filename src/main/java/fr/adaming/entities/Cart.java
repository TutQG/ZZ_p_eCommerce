package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

	// Attributs
	private List<OrderLine> listelico;

	// constructeurs
	public Cart() {
		super();
	}

	public Cart(List<OrderLine> listelico) {
		super();
		this.listelico = listelico;
	}

	// getters et seeters
	public List<OrderLine> getListelico() {
		return listelico;
	}

	public void setListelico(List<OrderLine> listelico) {
		this.listelico = listelico;
	}

	@Override
	public String toString() {
		return "Cart [listelico=" + listelico + "]";
	}

}
