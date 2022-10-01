package com.lucasdev3.crudbasicospring.resources;

import com.lucasdev3.crudbasicospring.dto.CreateUserRoleDTO;
import com.lucasdev3.crudbasicospring.models.LoginUserFormModel;
import com.lucasdev3.crudbasicospring.models.SaveUserFormModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import com.lucasdev3.crudbasicospring.services.CreateRoleUserService;
import com.lucasdev3.crudbasicospring.services.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    CreateUserService createUserService;

    @Autowired
    CreateRoleUserService createRoleUserService;

    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<ResponseModel> createUser(@RequestBody SaveUserFormModel userFormModel) {
        return createUserService.createUser(userFormModel);
    }

    @PostMapping("/role-user/create")
    @CrossOrigin
    public ResponseEntity<ResponseModel> createRole(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
        return createRoleUserService.execute(createUserRoleDTO);
    }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<ResponseModel> loginUser(@RequestBody LoginUserFormModel loginUserFormModel) {
        return createUserService.loginUser(loginUserFormModel);
    }

}
