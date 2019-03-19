package fr.adaming.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")

public class Customer implements Serializable {

	// attribut
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCusto")
	private int id;
	private String name;
	private String surname;
	private String mail;
	private String pwd;
	private String phoneNumber;

	@OneToMany(mappedBy = "custo", cascade = CascadeType.REMOVE)
	private List<Order> listOrder;

	// constructeur
	public Customer() {
		super();
	}

	public Customer(int id, String name, String surname, String mail, String pwd, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.pwd = pwd;
		this.phoneNumber = phoneNumber;
	}

	public Customer(String name, String surname, String mail, String pwd, String phoneNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.pwd = pwd;
		this.phoneNumber = phoneNumber;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
