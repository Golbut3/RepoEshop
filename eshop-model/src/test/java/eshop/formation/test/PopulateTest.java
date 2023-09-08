package eshop.formation.test;

import java.time.LocalDate;
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
import eshop.formation.model.Commande;
import eshop.formation.model.CommandeDetail;
import eshop.formation.model.Commentaire;
import eshop.formation.model.EtatCommande;
import eshop.formation.model.Fournisseur;
import eshop.formation.model.Produit;
import eshop.formation.model.Reparateur;

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
		Adresse adresse3 =  new Adresse("Place","Rouen","76000",client2);
		
		
		adresse.save(adresse1);
		adresse.save(adresse2);
		adresse.save(adresse3);

		
		List <Adresse> adresses = new ArrayList();
		adresses.add(adresse1);
		adresses.add(adresse2);
		adresses.add(adresse3);
		
		
		Fournisseur fournisseur1 = new Fournisseur("David","david@mail.com","ResponsableMan");
		fournisseur1.setAdresses(adresses);
		personne.save(fournisseur1);
		List<Fournisseur> fournisseurs = new ArrayList();
		fournisseurs.add(fournisseur1);
		adresse1.setFournisseurs(fournisseurs);
		
		
		Produit produit1 = new Produit("Prod1",30.0,50.5,"x254jpa","DeLuxe",120,fournisseur1);
		Produit produit2 = new Produit("Prod2",15.0,30.5,"x254jpa","DeBase",120,fournisseur1);
		Commande commande1= new Commande(LocalDate.parse("2023-09-08"),55.5,EtatCommande.ENCOURS,client1);
		Commande commande2= new Commande(LocalDate.parse("2023-09-10"),35.5,EtatCommande.ENCOURS,client1);

		CommandeDetail cm1= new CommandeDetail(1,55.5,commande1,produit1);
		CommandeDetail cm2= new CommandeDetail(1,35.5,commande2,produit2);

		List<CommandeDetail> details = new ArrayList<>();
		details.add(cm1);
		details.add(cm2);
		commande1.setDetails(details);
		
		List<Produit> produitsRepa = new ArrayList<Produit>();
		produitsRepa.add(produit1);
		produitsRepa.add(produit2);
		
		Reparateur rep1=new Reparateur("Denis","0252416356","PasOuf");
		Reparateur rep2=new Reparateur("Thierry","0252416355","VraimentPasOuf");

		rep1.setProduitsReparables(produitsRepa);
		rep2.setProduitsReparables(produitsRepa);
		
		List<Reparateur> reparateurs = new ArrayList();
		
		
		Commentaire commentaire1 = new Commentaire(LocalDate.parse("2023-09-08"),4,"Ouais pas mal",produit1,client1);
		List<Commentaire> commentaires = new ArrayList();
		produit1.setCommentaires(commentaires);
		reparateurs.add(rep1);
		reparateurs.add(rep2);
	
		produit.save(produit1);
		produit.save(produit2);
		commande.save(commande1);
		commande.save(commande2);
		commandeDetail.save(cm1);
		commandeDetail.save(cm2);
		reparateur.save(rep1);
		reparateur.save(rep2);
		commentaire.save(commentaire1);

		
	}
}
