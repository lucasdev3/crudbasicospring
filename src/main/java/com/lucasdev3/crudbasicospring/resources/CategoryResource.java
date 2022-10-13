package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.models.SaveCategoryModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import com.lucasdev3.crudbasicospring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private final CategoryService serviceCategory;

	public CategoryResource(CategoryService serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	@GetMapping
	@CrossOrigin
	public ResponseEntity<ResponseModel> findAll() {
		return serviceCategory.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@CrossOrigin
	public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
		return serviceCategory.findById(id);
	}
	
	@PostMapping(value = "/save")
	@CrossOrigin
	public ResponseEntity<?> save(@RequestBody SaveCategoryModel categoryModel) {
		return serviceCategory.save(categoryModel);
	}
}
