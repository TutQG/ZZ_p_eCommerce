package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	// déclaration attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCat")
	private int id;
	private String name;
	@Lob
	private byte[] photo;
	private String description;

	@OneToMany(mappedBy="cat", cascade=CascadeType.REMOVE)
	private List<Product> listProduct;
	
	@Transient
	private String img;

	// constructeurs
	public Category() {
		super();
	}

	public Category(String name, byte[] photo, String description) {
		super();
		this.name = name;
		this.photo = photo;
		this.description = description;
	}

	public Category(int id, String name, byte[] photo, String description) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.description = description;
	}

	// getter et setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
	
}
