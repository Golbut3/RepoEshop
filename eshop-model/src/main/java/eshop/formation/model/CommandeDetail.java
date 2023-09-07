package eshop.formation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
@Entity
@Table(name="detail_commande")
public class CommandeDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column
	private int quantite;
	@Column
	private Double prix;
	@Transient
	private Commande commande;
	@Transient
	private Produit produit;

	
	
	public CommandeDetail() {
	}

	public CommandeDetail(int quantite, Double prix, Commande commande, Produit produit) {
		this.quantite = quantite;
		this.prix = prix;
		this.commande = commande;
		this.produit = produit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	public CommandeDetail(Double prix, int quantite, Produit produit, Commande commande) {
		this.prix = prix;
		this.quantite = quantite;
		this.produit = produit;
		this.commande = commande;
	}

	@Override
	public String toString() {
		return this.prix + " euros, " + this.quantite + " produit(s) : " + this.produit;
	}
}
