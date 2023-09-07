package eshop.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.formation.model.Adresse;
import eshop.formation.model.CommandeDetail;


public interface IDAOCommandeDetail extends JpaRepository<CommandeDetail,Integer> {

}
