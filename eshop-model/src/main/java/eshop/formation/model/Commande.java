package eshop.formation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
@Entity
@Table(name="commande")
public class Commande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column
	private Date date;
	@Column
	private Double prixTotal;
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('ENCOURS', 'TERMINEE', 'ETAT3', 'ETAT4', 'ETAT5', 'ETAT6')")
	private EtatCommande etat;
	@Transient
	private Client client;
	@Transient
	private List<CommandeDetail> details = new ArrayList<>();

	
	
	public Commande() {
	}

	public Commande(Date date, Double prixTotal, EtatCommande etat, Client client) {
		this.date = date;
		this.prixTotal = prixTotal;
		this.etat = etat;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public EtatCommande getEtat() {
		return etat;
	}

	public void setEtat(EtatCommande etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandeDetail> details) {
		this.details = details;
	}
}
