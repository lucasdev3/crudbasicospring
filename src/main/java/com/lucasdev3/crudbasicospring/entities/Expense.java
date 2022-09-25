package com.lucasdev3.crudbasicospring.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_EXPENSE")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String expenseDescription;

    @Column
    private Double value;

    @Column
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) && Objects.equals(expenseDescription, expense.expenseDescription) && Objects.equals(value, expense.value) && Objects.equals(status, expense.status) && Objects.equals(category, expense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expenseDescription, value, status, category);
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseDescription='" + expenseDescription + '\'' +
                ", value=" + value +
                ", status='" + status + '\'' +
                ", category=" + category +
                '}';
    }
}
