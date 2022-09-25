package com.lucasdev3.crudbasicospring.repositories;

import com.lucasdev3.crudbasicospring.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
