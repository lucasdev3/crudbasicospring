package com.lucasdev3.crudbasicospring.repositories;

import com.lucasdev3.crudbasicospring.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
