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

import eshop.formation.dao.IDAOReparateur;
import eshop.formation.model.Reparateur;



@RestController
@RequestMapping("/reparateur")
public class ReparateurApiController {

	private IDAOReparateur daoReparateur;

	public ReparateurApiController(IDAOReparateur daoReparateur) {
		super();
		this.daoReparateur = daoReparateur;
	}

	@GetMapping("")
	//@JsonView(Views.Reparateur.class)
	public List<Reparateur> findAll() {
		return daoReparateur.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.ReparateurWithFiliere.class)
	public Reparateur findById(@PathVariable int id) {
		return daoReparateur.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Reparateur.class)
	public Reparateur create(@RequestBody Reparateur reparateur, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		reparateur = daoReparateur.save(reparateur);

		return reparateur;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Reparateur.class)
	public Reparateur update(@RequestBody Reparateur reparateur, @PathVariable int id) {
		reparateur = daoReparateur.save(reparateur);

		return reparateur;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Reparateur.class)
	public Reparateur partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Reparateur reparateur = this.daoReparateur.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Reparateur.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, reparateur, value);
		});
		
		Reparateur reparateurReturn = daoReparateur.save(reparateur);
		
		return reparateurReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoReparateur.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoReparateur.deleteById(id);
	}
}
