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

import eshop.formation.dao.IDAOPersonne;
import eshop.formation.model.Personne;



@RestController
@RequestMapping("/personne")
public class PersonneApiController {

	private IDAOPersonne daoPersonne;

	public PersonneApiController(IDAOPersonne daoPersonne) {
		super();
		this.daoPersonne = daoPersonne;
	}

	@GetMapping("")
	//@JsonView(Views.Personne.class)
	public List<Personne> findAll() {
		return daoPersonne.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.PersonneWithFiliere.class)
	public Personne findById(@PathVariable int id) {
		return daoPersonne.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Personne.class)
	public Personne create(@RequestBody Personne personne, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		personne = daoPersonne.save(personne);

		return personne;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Personne.class)
	public Personne update(@RequestBody Personne personne, @PathVariable int id) {
		personne = daoPersonne.save(personne);

		return personne;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Personne.class)
	public Personne partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Personne personne = this.daoPersonne.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Personne.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, personne, value);
		});
		
		Personne personneReturn = daoPersonne.save(personne);
		
		return personneReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoPersonne.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoPersonne.deleteById(id);
	}
}
