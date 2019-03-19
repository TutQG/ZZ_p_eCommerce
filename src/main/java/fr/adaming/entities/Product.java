package fr.adaming.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduct")
	private int id;
	private String designation;
	private String description;
	private double price;
	private int quantity;
	private boolean selectionne;
	@Lob
	private byte[] picture;
	
	@Transient
	private String img;

	@ManyToOne
	@JoinColumn(name = "catId", referencedColumnName = "idCat")
	private Category cat;

	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
	private List<OrderLine> listOL;

	public Product() {
		super();
	}

	public Product(int id, String designation, String description, double price, int quantity, boolean selectionne,
			byte[] picture) {
		super();
		this.id = id;
		this.designation = designation;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.selectionne = selectionne;
		this.picture = picture;
	}

	public Product(String designation, String description, double price, int quantity, boolean selectionne,
			byte[] picture) {
		super();
		this.designation = designation;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.selectionne = selectionne;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isSeclectionne() {
		return selectionne;
	}

	public void setSeclectionne(boolean seclectionne) {
		this.selectionne = seclectionne;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}

	public List<OrderLine> getListOL() {
		return listOL;
	}

	public void setListOL(List<OrderLine> listOL) {
		this.listOL = listOL;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
