package fr.adaming.service;


	import java.util.List;

	import fr.adaming.entities.OrderLine;
	import fr.adaming.entities.Product;


	public interface ICartService {
		
		public int ajoutOrdLine(OrderLine lico);
		public int ajoutPdt(Product p, int quantite);
		public int supprPdt(Product p);
		public List<OrderLine> recPdt();
		public int getTaille();
		public double getTotal();
		
		
	}