package eshop.formation.api;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eshop.formation.dao.IDAOCommande;
import eshop.formation.model.Commande;



@RestController
@RequestMapping("/commande")
public class CommandeApiController {

	private IDAOCommande daoCommande;

	public CommandeApiController(IDAOCommande daoCommande) {
		super();
		this.daoCommande = daoCommande;
	}

	@GetMapping("")
	//@JsonView(Views.Commande.class)
	public List<Commande> findAll() {
		return daoCommande.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.CommandeWithFiliere.class)
	public Commande findById(@PathVariable int id) {
		return daoCommande.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Commande.class)
	public Commande create(@RequestBody Commande commande, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		commande = daoCommande.save(commande);

		return commande;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Commande.class)
	public Commande update(@RequestBody Commande commande, @PathVariable int id) {
		commande = daoCommande.save(commande);

		return commande;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Commande.class)
	public Commande partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Commande commande = this.daoCommande.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commande.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, commande, value);
		});
		
		Commande commandeReturn = daoCommande.save(commande);
		
		return commandeReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoCommande.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoCommande.deleteById(id);
	}
}
