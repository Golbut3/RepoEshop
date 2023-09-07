package eshop.formation.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import eshop.formation.dao.IDAOAdresse;
import eshop.formation.dao.IDAOPersonne;
import eshop.formation.model.Adresse;
import eshop.formation.model.Client;

public class PopulateTest {
	
	@Autowired
	IDAOPersonne personne;
	@Autowired
	IDAOAdresse adresse;

	@Test
	public void populate() {

	}
	public void run(String ...args) {

		Client client1 = new Client("Doe","tahir@gmail.com","John");

		Adresse adresse1 =  new Adresse("6 Rue rougemont","Paris","75009",client1);
		adresse.save(adresse1);
		personne.save(client1);

	}
}
