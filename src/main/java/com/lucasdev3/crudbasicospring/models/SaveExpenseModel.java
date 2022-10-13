package com.lucasdev3.crudbasicospring.models;

import java.io.Serializable;
import java.util.Objects;

public class SaveExpenseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String expenseDescription;

    private Double value;

    private String status;

    private Integer categoryId;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaveExpenseModel that = (SaveExpenseModel) o;

        if (!Objects.equals(expenseDescription, that.expenseDescription))
            return false;
        if (!Objects.equals(value, that.value)) return false;
        if (!Objects.equals(status, that.status)) return false;
        if (!Objects.equals(categoryId, that.categoryId)) return false;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        int result = expenseDescription != null ? expenseDescription.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
