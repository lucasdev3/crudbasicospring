package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.config.SecurityConfiguration;
import com.lucasdev3.crudbasicospring.entities.Role;
import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.models.LoginUserFormModel;
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
import java.util.Objects;
import java.util.Optional;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityConfiguration sc;

    String rawPassword = "java2022";

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseModel> createUser(SaveUserFormModel userFormModel) {
        ResponseModel rm = new ResponseModel();
        User user = new User();
        try {

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

            Optional<User> existsUser = userRepository.findByUsername(userFormModel.getUsername());
            List<Role> idRoles = new ArrayList<>();
            if (existsUser.isPresent()) {
                rm.setStatusCode(401);
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                rm.setResponseDescription("Usuario ja cadastrado");
                return ResponseEntity.badRequest().body(rm);
            }
            user.setName(userFormModel.getName());
            user.setUsername(userFormModel.getUsername());
            idRoles.add(new Role(1, "USER"));
            user.setRoles(idRoles);

            BCryptPasswordEncoder bCryptPasswordEncoder = sc.bCryptPasswordEncoder();
            String encodedPassword =  bCryptPasswordEncoder.encode(userFormModel.getPassword());
            System.out.println("Encoded Password: " + encodedPassword);
            user.setPassword(encodedPassword);
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

    public ResponseEntity<ResponseModel> loginUser(LoginUserFormModel loginUserFormModel) {
        ResponseModel rm = new ResponseModel();
        User user = new User();

        try {

            if(loginUserFormModel.getUsername().isEmpty()) {
                rm.setMessage(HttpStatus.NO_CONTENT);
                rm.setResponseDescription("Usuario inválido");
                return ResponseEntity.badRequest().body(rm);
            }
            if(loginUserFormModel.getPassword().isEmpty()) {
                rm.setMessage(HttpStatus.NO_CONTENT);
                rm.setResponseDescription("Senha inválida");
                return ResponseEntity.badRequest().body(rm);
            }

            Optional<User> existsUser = userRepository.findByUsername(loginUserFormModel.getUsername());
            if (existsUser.isEmpty()) {
                rm.setStatusCode(401);
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                rm.setResponseDescription("Usuario não encontrado");
                return ResponseEntity.badRequest().body(rm);
            }

            String formPassword = loginUserFormModel.getPassword();
            BCryptPasswordEncoder bCryptPasswordEncoder = sc.bCryptPasswordEncoder();

            Boolean isEqualsPasswords = bCryptPasswordEncoder.matches(formPassword, existsUser.get().getPassword());

            if(Objects.equals(isEqualsPasswords, Boolean.FALSE)) {
                rm.setStatusCode(401);
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                rm.setResponseDescription("Senha inválida!");
                return ResponseEntity.badRequest().body(rm);
            }
            rm.setStatusCode(200);
            rm.setMessage(HttpStatus.OK);
            rm.setResponseDescription("Logado com sucesso!");
            return ResponseEntity.badRequest().body(rm);


        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return ResponseEntity.badRequest().body(rm);
        }
    }


}
