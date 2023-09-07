package eshop.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
@Entity
@DiscriminatorValue("Client")
public class Client extends Personne {
	@Column
	private String prenom;
	@Transient
	private List<Commande> commandes = new ArrayList<>();
	@Transient
	private List<Adresse> adresses;
	
	public Client() {}

	public Client(String nom, String mail,String prenom) {
		super(nom,mail);
		this.prenom = prenom;

	}
	
	public Client(Long id, String nom, String mail,String prenom) {
		super(id,nom,mail);
		this.prenom = prenom;

	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
}
