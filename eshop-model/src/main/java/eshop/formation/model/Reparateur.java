package eshop.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
@Entity
@Table(name = "reparateur")
public class Reparateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nom;
	@Version
	private int version;
	@Column
	private String telephone;
	@Column
	private String description;
	@Transient
	private List<Produit> produitsReparables = new ArrayList<>();
	
	

	public Reparateur() {
	}

	public Reparateur(String nom,String telephone, String description) {
		this.nom = nom;
		this.telephone = telephone;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getProduitsReparables() {
		return produitsReparables;
	}

	public void setProduitsReparables(List<Produit> produitsReparables) {
		this.produitsReparables = produitsReparables;
	}
}
