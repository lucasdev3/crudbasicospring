package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.dto.CreateUserRoleDTO;
import com.lucasdev3.crudbasicospring.models.SaveUserFormModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import com.lucasdev3.crudbasicospring.services.CreateRoleUserService;
import com.lucasdev3.crudbasicospring.services.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateRoleUserService createRoleUserService;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createUser(@RequestBody SaveUserFormModel userFormModel) {
        return createUserService.createUser(userFormModel);
    }

    @PostMapping("/role-user/create")
    public ResponseEntity<ResponseModel> createRole(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        return createRoleUserService.execute(createUserRoleDTO);
    }

}