package com.lucasdev3.crudbasicospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.models.SaveCategoryModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;

@RestController
@RequestMapping(value = "/categories")
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
			rm.setMessage(HttpStatus.OK);
			rm.setContentBodyResponse(list);
			return ResponseEntity.ok().body(rm);
		}
		rm.setStatusCode(400);
		rm.setMessage(HttpStatus.NOT_FOUND);
		rm.setContentBodyResponse(list);
		return ResponseEntity.badRequest().body(rm);
		
		
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category cat1 = new Category(1, "Electronics");
		return ResponseEntity.ok().body(cat1);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ResponseModel> save(SaveCategoryModel categoryModel) {
		ResponseModel rm = new ResponseModel();
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
			rm.setContentBodyResponse(e);
			return ResponseEntity.badRequest().body(rm);
		}
	}
}
