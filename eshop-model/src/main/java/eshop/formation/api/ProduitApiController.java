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

import eshop.formation.dao.IDAOProduit;
import eshop.formation.model.Produit;



@RestController
@RequestMapping("/produit")
public class ProduitApiController {

	private IDAOProduit daoProduit;

	public ProduitApiController(IDAOProduit daoProduit) {
		super();
		this.daoProduit = daoProduit;
	}

	@GetMapping("")
	//@JsonView(Views.Produit.class)
	public List<Produit> findAll() {
		return daoProduit.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.ProduitWithFiliere.class)
	public Produit findById(@PathVariable int id) {
		return daoProduit.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Produit.class)
	public Produit create(@RequestBody Produit produit, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		produit = daoProduit.save(produit);

		return produit;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Produit.class)
	public Produit update(@RequestBody Produit produit, @PathVariable int id) {
		produit = daoProduit.save(produit);

		return produit;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Produit.class)
	public Produit partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Produit produit = this.daoProduit.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Produit.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, produit, value);
		});
		
		Produit produitReturn = daoProduit.save(produit);
		
		return produitReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoProduit.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoProduit.deleteById(id);
	}
}
