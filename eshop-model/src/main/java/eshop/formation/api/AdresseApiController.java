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

import eshop.formation.dao.IDAOAdresse;
import eshop.formation.model.Adresse;



@RestController
@RequestMapping("/adresse")
public class AdresseApiController {

	private IDAOAdresse daoAdresse;

	public AdresseApiController(IDAOAdresse daoAdresse) {
		super();
		this.daoAdresse = daoAdresse;
	}

	@GetMapping("")
	//@JsonView(Views.Adresse.class)
	public List<Adresse> findAll() {
		return daoAdresse.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.AdresseWithFiliere.class)
	public Adresse findById(@PathVariable int id) {
		return daoAdresse.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Adresse.class)
	public Adresse create(@RequestBody Adresse adresse, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		adresse = daoAdresse.save(adresse);

		return adresse;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Adresse.class)
	public Adresse update(@RequestBody Adresse adresse, @PathVariable int id) {
		adresse = daoAdresse.save(adresse);

		return adresse;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Adresse.class)
	public Adresse partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Adresse adresse = this.daoAdresse.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Adresse.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, adresse, value);
		});
		
		Adresse adresseReturn = daoAdresse.save(adresse);
		
		return adresseReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoAdresse.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoAdresse.deleteById(id);
	}
}
