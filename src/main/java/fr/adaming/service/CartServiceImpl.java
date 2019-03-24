package fr.adaming.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.entities.OrderLine;
import fr.adaming.entities.Product;

@Service("cartService")
@Transactional
public class CartServiceImpl implements ICartService{
	//List<LigneCommande> articles = new ArrayList<LigneCommande>();
    Map<Integer, OrderLine> articles = new HashMap<Integer, OrderLine>();
	
	@Override
	public int ajoutPdt(Product pdt, int quantity) {
		OrderLine lc = articles.get(pdt.getId());
		if(lc==null){
			OrderLine lcOut = new OrderLine();
			lcOut.setProduct(pdt);
			lcOut.setQuatity(quantity);
			lcOut.setPrice(pdt.getPrice()*quantity);
			articles.put(pdt.getId(), lcOut);
		}else{
			lc.setQuatity(lc.getQuatity()+quantity);
		}
		return lc.getId();
	}

	@Override
	public int supprPdt(Product pdt) {
		articles.remove(pdt.getId());
		return 0;
	}

	@Override
	public List<OrderLine> recPdt() {
		return null;
	// A REVOIR 
	}

	@Override
	public int getTaille() {
		return articles.size();
	}

	@Override
	public double getTotal() {
		double total=0;
		for(OrderLine lc:articles.values()){
			total=lc.getPrice()*lc.getQuatity();
		}
		return total;
	}

	@Override
	public int ajoutOrdLine(OrderLine lico) {
		// TODO Auto-generated method stub
		return 0;
	}


}