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

import eshop.formation.dao.IDAOCommentaire;
import eshop.formation.model.Commentaire;



@RestController
@RequestMapping("/commentaire")
public class CommentaireApiController {

	private IDAOCommentaire daoCommentaire;

	public CommentaireApiController(IDAOCommentaire daoCommentaire) {
		super();
		this.daoCommentaire = daoCommentaire;
	}

	@GetMapping("")
	//@JsonView(Views.Commentaire.class)
	public List<Commentaire> findAll() {
		return daoCommentaire.findAll();
	}

	@GetMapping("/{id}")
	//@JsonView(Views.CommentaireWithFiliere.class)
	public Commentaire findById(@PathVariable int id) {
		return daoCommentaire.findById(id).get();
	}

	@PostMapping("")
	//@JsonView(Views.Commentaire.class)
	public Commentaire create(@RequestBody Commentaire commentaire, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matière invalide");
		}

		commentaire = daoCommentaire.save(commentaire);

		return commentaire;
	}

	@PutMapping("/{id}")
	//@JsonView(Views.Commentaire.class)
	public Commentaire update(@RequestBody Commentaire commentaire, @PathVariable int id) {
		commentaire = daoCommentaire.save(commentaire);

		return commentaire;
	}

	@PatchMapping("/{id}")
	//@JsonView(Views.Commentaire.class)
	public Commentaire partialEdit(@RequestBody Map<String, Object> fields, @PathVariable int id) {
		Commentaire commentaire = this.daoCommentaire.findById(id).get();
		
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(Commentaire.class, key);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, commentaire, value);
		});
		
		Commentaire commentaireReturn = daoCommentaire.save(commentaire);
		
		return commentaireReturn;
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable int id) {
		if(!daoCommentaire.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		daoCommentaire.deleteById(id);
	}
}
