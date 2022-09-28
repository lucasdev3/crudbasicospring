package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.entities.Role;
import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.repositories.UserRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ResponseModel> execute(User user) {
        ResponseModel rm = new ResponseModel();
        try {
            User existsUser = userRepository.findByUsername(user.getUsername());
            List<Role> idRoles = new ArrayList<>();
            if (existsUser != null) {
                rm.setStatusCode(401);
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                return ResponseEntity.badRequest().body(rm);
            }
            idRoles.add(new Role(1, "USER"));
            user.setRoles(idRoles);
            userRepository.save(user);
            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.CREATED);
            rm.setContentBodyResponse(user);
            return ResponseEntity.ok().body(rm);
        } catch (Error e) {
            System.out.println("Erro: " + e);
            rm.setStatusCode(500);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
