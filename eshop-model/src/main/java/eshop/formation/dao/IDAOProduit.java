package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Adresse;
import eshop.formation.model.Produit;


public interface IDAOProduit extends JpaRepository<Produit,Integer> {

}
