package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.entities.Category;
import com.lucasdev3.crudbasicospring.entities.Expense;
import com.lucasdev3.crudbasicospring.models.SaveExpenseModel;
import com.lucasdev3.crudbasicospring.repositories.CategoryRepository;
import com.lucasdev3.crudbasicospring.repositories.ExpenseRepository;
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
@RequestMapping(value = "/expenses")
public class ExpenseResource {

    @Autowired
    private ExpenseRepository repoExpense;

    @Autowired
    private CategoryRepository repoCategory;

    @GetMapping
    public ResponseEntity<ResponseModel> findAll() {
        ResponseModel rm = new ResponseModel();
        List<Expense> list = repoExpense.findAll();
        if(list.size() > 0) {
            System.out.println("Lista de despesas: ");
            for(Expense expense : list) {
                System.out.println(expense);
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
        ResponseModel rm = new ResponseModel();
        if(!(id > 0)) {
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.CONFLICT);
            return ResponseEntity.badRequest().body(rm);
        }
        Optional<Expense> expense = repoExpense.findById(id);
        if(expense.isEmpty()) {
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.NOT_FOUND);
            return ResponseEntity.badRequest().body(rm);
        }
        rm.setStatusCode(200);
        rm.setMessage(HttpStatus.FOUND);
        rm.setContentBodyResponse(expense);
        return ResponseEntity.ok().body(rm);
    }

    @Transactional
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseModel> save(@RequestBody SaveExpenseModel expenseModel) {
        ResponseModel rm = new ResponseModel();
        if(Objects.isNull(expenseModel)) {
            rm.setStatusCode(500);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(rm);
        }
        try {
            Expense expense = new Expense();
            Integer categoryId = expenseModel.getCategoryId();
            Category category = repoCategory.findById(categoryId).orElse(null);

            if(Objects.isNull(category)) {
                rm.setStatusCode(400);
                rm.setMessage(HttpStatus.NOT_FOUND);
                return ResponseEntity.badRequest().body(null);
            }

            expense.setExpenseDescription(expenseModel.getExpenseDescription());
            expense.setStatus(expenseModel.getStatus());
            expense.setValue(expenseModel.getValue());
            expense.setCategory(category);

            Expense saveExpense = repoExpense.save(expense);

            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.CREATED);
            rm.setContentBodyResponse(saveExpense);
            return ResponseEntity.ok().body(rm);
        } catch (Exception e) {
            System.out.println("Falha ao cadastrar despesa: " + e);
            rm.setStatusCode(400);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            rm.setContentBodyResponse(null);
            return ResponseEntity.badRequest().body(rm);
        }
    }

}
