package com.lucasdev3.crudbasicospring.services;

import com.lucasdev3.crudbasicospring.dto.CreateUserRoleDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreateRoleUserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ResponseModel> execute(CreateUserRoleDTO createUserRoleDTO) {
        ResponseModel rm = new ResponseModel();
        try {
            Optional<User> userExists = userRepository.findById(createUserRoleDTO.getIdUser());
            List<Role> roles = new ArrayList<>();

            if (userExists.isEmpty()) {
                rm.setMessage(HttpStatus.UNAUTHORIZED);
                rm.setResponseDescription("Usuario não cadastrado!");
                rm.setContentBodyResponse(null);
                return ResponseEntity.badRequest().body(rm);
            }
            roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
                return new Role(role);
            }).collect(Collectors.toList());
            User user = userExists.get();
            user.setRoles(roles);
            userRepository.save(user);
            rm.setMessage(HttpStatus.OK);
            rm.setContentBodyResponse(user);
            return ResponseEntity.ok().body(rm);
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            rm.setMessage(HttpStatus.BAD_REQUEST);
            rm.setContentBodyResponse(null);
            return ResponseEntity.badRequest().body(rm);
        }
    }
}
