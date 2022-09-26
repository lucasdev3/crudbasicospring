package com.lucasdev3.crudbasicospring.repositories;

import com.lucasdev3.crudbasicospring.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Integer> {

}
