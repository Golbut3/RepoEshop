package eshop.formation.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eshop.formation.dao.IDAOAdresse;
import eshop.formation.dao.IDAOCommande;
import eshop.formation.dao.IDAOCommandeDetail;
import eshop.formation.dao.IDAOCommentaire;
import eshop.formation.dao.IDAOPersonne;
import eshop.formation.dao.IDAOProduit;
import eshop.formation.dao.IDAOReparateur;
import eshop.formation.model.Adresse;
import eshop.formation.model.Client;
import eshop.formation.model.Fournisseur;

public class PopulateTest {
	
	@Autowired
	IDAOPersonne personne;
	
	@Autowired
	IDAOAdresse adresse;
	
	@Autowired
	IDAOCommande commande;
	
	@Autowired
	IDAOCommentaire commentaire;
	
	@Autowired
	IDAOCommandeDetail commandeDetail;
	
	@Autowired
	IDAOProduit produit;
	
	@Autowired
	IDAOReparateur reparateur;

	@Test
	public void populate() {

	}
	public void run(String ...args) {

		Client client1 = new Client("Doe","john@gmail.com","John");
		Client client2 = new Client("Doe","tahir@gmail.com","Tahir");
		personne.save(client1);
		personne.save(client2);
		
		Adresse adresse1 =  new Adresse("6 Rue rougemont","Paris","75009",client1);
		Adresse adresse2 =  new Adresse("6 Rue république","Paris","75100",client1);
		Adresse adresse3 =  new Adresse("Place","Rouen","76000");
		
		
		adresse.save(adresse1);
		adresse.save(adresse2);
		
		List <Adresse> adresses = new ArrayList();
		
		//Adresse adresse3 =  new Adresse("6 Rue rougemont","Paris","75009",client1);
		Fournisseur fournisseur1 = new Fournisseur();
		fournisseur1.setAdresses(null);
		
		
	}
}
