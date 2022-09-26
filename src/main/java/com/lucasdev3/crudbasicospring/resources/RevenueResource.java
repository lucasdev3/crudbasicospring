package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.models.SaveRevenueModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import com.lucasdev3.crudbasicospring.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/revenues")
public class RevenueResource {

    @Autowired
    private RevenueService revenueService;

    @GetMapping
    @CrossOrigin
    public ResponseEntity<ResponseModel> findAll() {
        return revenueService.findAll();
    }

    @GetMapping(value = "/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseModel> findById(@PathVariable Integer id) {
        return revenueService.findById(id);
    }

    @PostMapping(value = "/save")
    @CrossOrigin
    public ResponseEntity<ResponseModel> save(@RequestBody SaveRevenueModel saveRevenueModel) {
        System.out.println(saveRevenueModel.toString());
        return revenueService.save(saveRevenueModel);
    }

}
