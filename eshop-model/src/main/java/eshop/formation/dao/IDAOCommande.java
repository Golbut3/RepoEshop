package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Commande;


public interface IDAOCommande extends JpaRepository<Commande,Integer> {

}
