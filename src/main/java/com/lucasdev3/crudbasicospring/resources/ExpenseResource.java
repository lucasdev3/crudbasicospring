package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.models.SaveExpenseModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import com.lucasdev3.crudbasicospring.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseResource {

    @Autowired
    private final ExpenseService expenseService;

    public ExpenseResource(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<ResponseModel> findAll() {
        return expenseService.findAll();
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
        return expenseService.findById(id);
    }

    @PostMapping(value = "/save")
    @CrossOrigin
    public ResponseEntity<ResponseModel> save(@RequestBody SaveExpenseModel expenseModel) {
        return expenseService.save(expenseModel);
    }

}
