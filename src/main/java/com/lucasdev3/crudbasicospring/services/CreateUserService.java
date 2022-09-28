package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.entities.Role;
import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.models.SaveUserFormModel;
import com.lucasdev3.crudbasicospring.repositories.UserRepository;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseModel> createUser(SaveUserFormModel userFormModel) {
        ResponseModel rm = new ResponseModel();
        User user = new User();
        try {
            Optional<User> existsUser = userRepository.findByUsername(userFormModel.getUsername());
            List<Role> idRoles = new ArrayList<>();
            if (existsUser.isPresent()) {
                rm.setStatusCode(401);
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                rm.setResponseDescription("Usuario ja cadastrado");
                return ResponseEntity.badRequest().body(rm);
            }
            if(userFormModel.getName().isEmpty()) {
                rm.setMessage(HttpStatus.NO_CONTENT);
                rm.setResponseDescription("Nome inválido");
                return ResponseEntity.badRequest().body(rm);
            }
            if(userFormModel.getUsername().isEmpty()) {
                rm.setMessage(HttpStatus.NO_CONTENT);
                rm.setResponseDescription("Usuario inválido");
                return ResponseEntity.badRequest().body(rm);
            }
            if(userFormModel.getPassword().isEmpty()) {
                rm.setMessage(HttpStatus.NO_CONTENT);
                rm.setResponseDescription("Senha inválida");
                return ResponseEntity.badRequest().body(rm);
            }
            user.setName(userFormModel.getName());
            user.setUsername(userFormModel.getUsername());
            idRoles.add(new Role(1, "USER"));
            user.setRoles(idRoles);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(userFormModel.getPassword()));
            userRepository.save(user);
            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.CREATED);
            rm.setResponseDescription("Usuario cadastrado com sucesso");
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
