package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Adresse;
import eshop.formation.model.Commentaire;


public interface IDAOCommentaire extends JpaRepository<Commentaire,Integer> {

}
