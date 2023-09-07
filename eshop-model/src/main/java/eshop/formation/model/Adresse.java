package eshop.formation.model;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
@Entity
@Table(name = "adresse")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Version
	private int version;
	@Column
	private String rue;
	@Column
	private String ville;
	@Column
	private String codePostal;
	@ManyToOne
	@JoinColumn(name = "client")
	private Client client;

	@ManyToMany
	@JoinTable(name="adresse_fournisseur",joinColumns = @JoinColumn(name="adresse"),inverseJoinColumns = @JoinColumn(name="fournisseur"))
	private List<Fournisseur> fournisseurs = new ArrayList<>();
	
	public Adresse() {
		
	}

	
	
	public Adresse(String rue, String ville, String codePostal, Client client) {
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.client = client;
	}



	public Adresse(Long id, String rue, String ville, String codePostal, Client client) {
		this.id = id;
		this.rue = rue;
		this.ville = ville;
		this.codePostal = codePostal;
		this.client = client;

	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
}
