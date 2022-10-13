package com.lucasdev3.crudbasicospring.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_EXPENSE")
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String expenseDescription;

    @Column(nullable = false, precision = 11, scale = 2)
    private Double value;

    @Column(nullable = false, length = 15)
    private String status;

    @JoinColumn(name = "category_id", nullable = false, table = "TB_CATEGORIAS")
    private Category categoryExpense;

    @JoinColumn(name = "id", nullable = false, table = "TB_USERS")
    private User user;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategoryExpense() {
        return categoryExpense;
    }

    public void setCategoryExpenseId(Category categoryExpense) {
        this.categoryExpense = categoryExpense;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (!Objects.equals(id, expense.id)) return false;
        if (!Objects.equals(expenseDescription, expense.expenseDescription))
            return false;
        if (!Objects.equals(value, expense.value)) return false;
        if (!Objects.equals(status, expense.status)) return false;
        if (!Objects.equals(categoryExpense, expense.categoryExpense))
            return false;
        return Objects.equals(user, expense.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (expenseDescription != null ? expenseDescription.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (categoryExpense != null ? categoryExpense.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
