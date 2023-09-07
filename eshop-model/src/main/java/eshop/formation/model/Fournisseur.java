package eshop.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
@Entity
@DiscriminatorValue("Fournisseur")
public class Fournisseur extends Personne {
	@Column
	private String responsable;
	@OneToMany(mappedBy = "fournisseur")
	private List<Produit> produits = new ArrayList<>();
	@ManyToMany(mappedBy = "fournisseurs")
	private List<Adresse> adresses = new ArrayList<>();

	
	
	public Fournisseur() {
	}

	public Fournisseur(String nom, String mail, String responsable) {
		super(nom, mail);
		this.responsable=responsable;
		}

	
	public Fournisseur(Long id, String nom, String mail, String responsable) {
		super(id, nom, mail);
		this.responsable=responsable;
		}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return " > " + this.id + ". " + this.nom;
	}
}
