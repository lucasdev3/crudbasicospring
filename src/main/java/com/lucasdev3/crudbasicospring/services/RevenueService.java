package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.entities.Revenue;
import com.lucasdev3.crudbasicospring.models.SaveRevenueModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.repositories.RevenueRepository;
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

    public RevenueService(RevenueRepository repoRevenue, CategoryRepository repoCategory) {
        this.repoRevenue = repoRevenue;
        this.repoCategory = repoCategory;
    }

    public ResponseEntity<ResponseModel> findAll() {
        ResponseModel rm = new ResponseModel();
        List<Revenue> list = repoRevenue.findAll();
        if(list.size() > 0) {
            System.out.println("Lista de receitas: ");
            for(Revenue revenue : list) {
                System.out.println(revenue);
            }
            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.FOUND);
            rm.setContentBodyResponse(list);
            return ResponseEntity.ok().body(rm);
        }
        rm.setStatusCode(400);
        rm.setMessage(HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(rm);
    }

    public ResponseEntity<ResponseModel> findById(Integer id) {
        ResponseModel rm = new ResponseModel();
        if(!(id > 0)) {
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.CONFLICT);
            return ResponseEntity.badRequest().body(rm);
        }
        Optional<Revenue> revenue = repoRevenue.findById(id);
        if(revenue.isEmpty()) {
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body(rm);
        }
        rm.setStatusCode(200);
        rm.setMessage(HttpStatus.FOUND);
        rm.setContentBodyResponse(revenue);
        return ResponseEntity.ok().body(rm);
    }

    @Transactional
    public ResponseEntity<ResponseModel> save(SaveRevenueModel saveRevenueModel) {
        ResponseModel rm = new ResponseModel();
        if(Objects.isNull(saveRevenueModel)) {
            rm.setStatusCode(500);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(rm);
        }
        try {
            Revenue revenue = new Revenue();
            Integer categoryId = saveRevenueModel.getCategoryId();
            Category category = repoCategory.findById(categoryId).orElse(null);

            if(Objects.isNull(category)) {
                rm.setStatusCode(400);
                rm.setMessage(HttpStatus.NOT_FOUND);
                return ResponseEntity.badRequest().body(null);
            }

            revenue.setRevenueDescription(saveRevenueModel.getRevenueDescription());
            revenue.setStatus(saveRevenueModel.getStatus());
            revenue.setValue(saveRevenueModel.getValue());
            if(!category.getTypeCategory().equalsIgnoreCase("REVENUE")) {
                rm.setStatusCode(400);
                rm.setMessage(HttpStatus.NOT_ACCEPTABLE);
                return ResponseEntity.badRequest().body(rm);
            }
            revenue.setCategoryRevenue(category);
            Revenue saveRevenue = repoRevenue.save(revenue);
            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.CREATED);
            rm.setContentBodyResponse(saveRevenue);
            return ResponseEntity.ok().body(rm);
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar receita: " + e);
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            rm.setContentBodyResponse(null);
            return ResponseEntity.badRequest().body(rm);
        }
    }

}
