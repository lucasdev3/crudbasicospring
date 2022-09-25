package com.lucasdev3.crudbasicospring.models;

import java.io.Serializable;
import java.util.Objects;

public class SaveExpenseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String expenseDescription;

    private Double value;

    private String status;

    private Integer categoryId;

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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveExpenseModel that = (SaveExpenseModel) o;
        return Objects.equals(expenseDescription, that.expenseDescription) && Objects.equals(value, that.value) && Objects.equals(status, that.status) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseDescription, value, status, categoryId);
    }


}
