package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.models.SaveCategoryModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
@Transactional
public class CategoryResource {
	
	@Autowired
	private CategoryRepository repoCategory;
	
	@GetMapping
	public ResponseEntity<ResponseModel> findAll() {
		ResponseModel rm = new ResponseModel();
		List<Category> list = repoCategory.findAll();
		if(list.size() > 0) {
			System.out.println("Lista de categorias: ");
			for(Category category : list) {
				System.out.println(category);
			}
			rm.setStatusCode(200);
			rm.setMessage(HttpStatus.FOUND);
			rm.setContentBodyResponse(list);
			return ResponseEntity.ok().body(rm);
		}
		rm.setStatusCode(400);
		rm.setMessage(HttpStatus.NOT_FOUND);
		rm.setContentBodyResponse(list);
		return ResponseEntity.badRequest().body(rm);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
		ResponseModel rm = new ResponseModel();
		if(!(id > 0)) {
			rm.setStatusCode(400);
			rm.setMessage(HttpStatus.CONFLICT);
			return ResponseEntity.badRequest().body(rm);
		}
		Optional<Category> category = repoCategory.findById(id);
		if(category.isEmpty()) {
			rm.setStatusCode(400);
			rm.setMessage(HttpStatus.NOT_FOUND);
			return ResponseEntity.badRequest().body(rm);
		}
		rm.setStatusCode(200);
		rm.setMessage(HttpStatus.FOUND);
		rm.setContentBodyResponse(category);
		return ResponseEntity.ok().body(rm);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ResponseModel> save(@RequestBody SaveCategoryModel categoryModel) {
		ResponseModel rm = new ResponseModel();
		if(Objects.isNull(categoryModel)) {
			rm.setStatusCode(500);
			rm.setMessage(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(rm);
		}
		try {
			Category category = new Category();
			category.setName(categoryModel.getName());
			Category saveCategory = repoCategory.save(category);
			rm.setStatusCode(200);
			rm.setMessage(HttpStatus.CREATED);
			rm.setContentBodyResponse(saveCategory);
			return ResponseEntity.ok().body(rm);
		} catch (Exception e) {
			System.out.println("Falha ao cadastrar categoria: " + e);
			rm.setStatusCode(400);
			rm.setMessage(HttpStatus.BAD_REQUEST);
			rm.setContentBodyResponse(null);
			return ResponseEntity.badRequest().body(rm);
		}
	}
}
