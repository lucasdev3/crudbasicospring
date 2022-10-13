package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.models.SaveCategoryModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.repositories.UserRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.lucasdev3.crudbasicospring.utils.ValidationUtils.validateCategoryModel;

@Service

public class CategoryService {

    @Autowired
    private final CategoryRepository repoCategory;

    @Autowired
    private final UserRepository repoUser;

    public CategoryService(CategoryRepository repoCategory, UserRepository repoUser) {
        this.repoCategory = repoCategory;
        this.repoUser = repoUser;
    }

    public ResponseEntity<ResponseModel> findAll() {
        ResponseModel rm = new ResponseModel();
        List<Category> list = repoCategory.findAll();
        if(list.size() > 0) {
            System.out.println("Lista de categorias: ");
            for(Category category : list) {
                System.out.println(category);
            }
            rm.setMessage(HttpStatus.FOUND);
            rm.setContentBodyResponse(list);
            rm.setResponseDescription("Categorias encontradas!");
            return ResponseEntity.ok().body(rm);
        }
        rm.setMessage(HttpStatus.NOT_FOUND);
        rm.setContentBodyResponse(list);
        rm.setResponseDescription("Nenhuma categoria cadastrada!");
        return ResponseEntity.badRequest().body(rm);
    }

    public ResponseEntity<ResponseModel> findById(Integer id) {
        ResponseModel rm = new ResponseModel();
        if(!(id > 0)) {
            rm.setMessage(HttpStatus.CONFLICT);
            return ResponseEntity.badRequest().body(rm);
        }
        Optional<Category> category = repoCategory.findById(id);
        if(category.isEmpty()) {
            rm.setMessage(HttpStatus.NOT_FOUND);
            rm.setResponseDescription("Nenhuma categoria cadastrada!");
            return ResponseEntity.badRequest().body(rm);
        }
        rm.setMessage(HttpStatus.FOUND);
        rm.setContentBodyResponse(category);
        return ResponseEntity.ok().body(rm);
    }

    public ResponseEntity<?> save(SaveCategoryModel categoryModel) {
        ResponseModel rm = new ResponseModel();
        Category category = new Category();
        try {
            Optional<User> userFound= repoUser.findById(categoryModel.getUserId());
            if(userFound.isEmpty()) {
                rm.setMessage(HttpStatus.NOT_ACCEPTABLE);
                rm.setResponseDescription("Usuário inválido!");
                return ResponseEntity.badRequest().body(rm);
            }
            List<ResponseModel> responseModelList = validateCategoryModel(categoryModel, userFound.get());
            if (Objects.nonNull(responseModelList)) {
                return ResponseEntity.badRequest().body(responseModelList);
            }
            category.setName(categoryModel.getName());
            category.setTypeCategory(categoryModel.getTypeCategory());
            category.setUser(userFound.get());

            Category saveCategory = repoCategory.save(category);

            rm.setMessage(HttpStatus.CREATED);
            rm.setContentBodyResponse(saveCategory);
            rm.setResponseDescription("Categoria cadastrada!");
            return ResponseEntity.ok().body(rm);
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar categoria: " + e);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            rm.setContentBodyResponse(null);
            rm.setResponseDescription("Falha ao cadastrar categoria!");
            return ResponseEntity.badRequest().body(rm);
        }
    }
}
