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

import eshop.formation.dao.IDAOCommandeDetail;
import eshop.formation.model.CommandeDetail;



@RestController
@RequestMapping("/commandeDetail")
public class CommandeDetailApiController {

	private IDAOCommandeDetail daoCommandeDetail;

	public CommandeDetailApiController(IDAOCommandeDetail daoCommandeDetail) {
		super();
		this.daoCommandeDetail = daoCommandeDetail;
	}

	@GetMapping("")
	//@JsonView(Views.CommandeDetail.class)
	public List<CommandeDetail> findAll() {
		return daoCommandeDetail.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.CommandeDetailWithFiliere.class)
	public CommandeDetail findById(@PathVariable int id) {
		return daoCommandeDetail.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.CommandeDetail.class)
	public CommandeDetail create(@RequestBody CommandeDetail commandeDetail, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		commandeDetail = daoCommandeDetail.save(commandeDetail);

		return commandeDetail;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.CommandeDetail.class)
	public CommandeDetail update(@RequestBody CommandeDetail commandeDetail, @PathVariable int id) {
		commandeDetail = daoCommandeDetail.save(commandeDetail);

		return commandeDetail;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.CommandeDetail.class)
	public CommandeDetail partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		CommandeDetail commandeDetail = this.daoCommandeDetail.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(CommandeDetail.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, commandeDetail, value);
		});
		
		CommandeDetail commandeDetailReturn = daoCommandeDetail.save(commandeDetail);
		
		return commandeDetailReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoCommandeDetail.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoCommandeDetail.deleteById(id);
	}
}
