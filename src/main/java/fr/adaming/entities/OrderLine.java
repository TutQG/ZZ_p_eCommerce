package fr.adaming.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="orderLines")
public class OrderLine implements Serializable {

	
	private static final long serialVersionUID = 1L;
	//attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idOL")
	private int id;
	private int quatity;
	private double price;
	
	@ManyToOne
	@JoinColumn(name="productId", referencedColumnName="idProduct")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId",referencedColumnName="idOrder")
	private Order order;
	
	//constructeurs
	public OrderLine() {
		super();
	}
	public OrderLine(int quatity, double price) {
		super();
		this.quatity = quatity;
		this.price = price;
	}
	
	
	
	public OrderLine(int id, int quatity, double price) {
		super();
		this.id = id;
		this.quatity = quatity;
		this.price = price;
	}
	//getter et setter
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
