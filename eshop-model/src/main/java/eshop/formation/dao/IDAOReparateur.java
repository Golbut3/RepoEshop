package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Adresse;
import eshop.formation.model.Reparateur;


public interface IDAOReparateur extends JpaRepository<Reparateur,Integer> {

}
