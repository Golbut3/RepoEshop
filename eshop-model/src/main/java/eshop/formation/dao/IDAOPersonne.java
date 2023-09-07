package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Adresse;
import eshop.formation.model.Personne;


public interface IDAOPersonne extends JpaRepository<Personne,Integer> {

}
