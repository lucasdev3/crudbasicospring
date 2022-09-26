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
	private CategoryService serviceCategory;
	
	@GetMapping
	public ResponseEntity<ResponseModel> findAll() {
		return serviceCategory.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
		return serviceCategory.findById(id);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<ResponseModel> save(@RequestBody SaveCategoryModel categoryModel) {
		return serviceCategory.save(categoryModel);
	}
}
