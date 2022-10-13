package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.entities.Revenue;
import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.models.SaveRevenueModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.repositories.RevenueRepository;
import com.lucasdev3.crudbasicospring.repositories.UserRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RevenueService {

    @Autowired
    private final RevenueRepository repoRevenue;

    @Autowired
    private final CategoryRepository repoCategory;

    @Autowired
    private final UserRepository repoUser;


    public RevenueService(RevenueRepository repoRevenue, CategoryRepository repoCategory, UserRepository repoUser) {
        this.repoRevenue = repoRevenue;
        this.repoCategory = repoCategory;
        this.repoUser = repoUser;
    }

    public ResponseEntity<ResponseModel> findAll() {
        ResponseModel rm = new ResponseModel();
        List<Revenue> list = repoRevenue.findAll();
        if(list.size() > 0) {
            System.out.println("Lista de receitas: ");
            for(Revenue revenue : list) {
                System.out.println(revenue);
            }
            rm.setMessage(HttpStatus.FOUND);
            rm.setContentBodyResponse(list);
            return ResponseEntity.ok().body(rm);
        }
        rm.setMessage(HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(rm);
    }

    public ResponseEntity<ResponseModel> findById(Integer id) {
        ResponseModel rm = new ResponseModel();
        if(!(id > 0)) {
            rm.setMessage(HttpStatus.CONFLICT);
            return ResponseEntity.badRequest().body(rm);
        }
        Optional<Revenue> revenue = repoRevenue.findById(id);
        if(revenue.isEmpty()) {
            rm.setMessage(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body(rm);
        }
        rm.setMessage(HttpStatus.FOUND);
        rm.setContentBodyResponse(revenue);
        return ResponseEntity.ok().body(rm);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save(SaveRevenueModel saveRevenueModel) {
        ResponseModel rm = new ResponseModel();
        if(Objects.isNull(saveRevenueModel)) {
            rm.setMessage(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(rm);
        }
        try {
            Revenue revenue = new Revenue();
            Integer categoryId = saveRevenueModel.getCategoryId();
            Category category = repoCategory.findById(categoryId).orElse(null);
            Optional<User> userFound= repoUser.findById(saveRevenueModel.getCategoryId());

            if(Objects.isNull(category)) {
                rm.setMessage(HttpStatus.NOT_FOUND);
                return ResponseEntity.badRequest().body(null);
            }

            if(userFound.isEmpty()) {
                rm.setMessage(HttpStatus.NOT_ACCEPTABLE);
                rm.setResponseDescription("Despesa inválida!");
                return ResponseEntity.badRequest().body(rm);
            }

            revenue.setRevenueDescription(saveRevenueModel.getRevenueDescription());
            revenue.setStatus(saveRevenueModel.getStatus());
            revenue.setValue(saveRevenueModel.getValue());

            if(!Objects.equals(category.getUser(), userFound.get())) {
                rm.setMessage(HttpStatus.NOT_ACCEPTABLE);
                rm.setResponseDescription("Categoria não foi cadastrada!");
                return ResponseEntity.badRequest().body(rm);
            }

            if(!category.getTypeCategory().equalsIgnoreCase("REVENUE")) {
                rm.setMessage(HttpStatus.NOT_ACCEPTABLE);
                rm.setResponseDescription("Tipo de categoria inválida.!");
                return ResponseEntity.badRequest().body(rm);
            }

            revenue.setCategoryRevenue(category);
            revenue.setUser(userFound.get());
            Revenue saveRevenue = repoRevenue.save(revenue);
            rm.setMessage(HttpStatus.CREATED);
            rm.setContentBodyResponse(saveRevenue);
            return ResponseEntity.ok().body(rm);
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar receita: " + e);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            rm.setContentBodyResponse(null);
            return ResponseEntity.badRequest().body(rm);
        }
    }

}
