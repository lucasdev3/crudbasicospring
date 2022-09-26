package com.lucasdev3.crudbasicospring.models;

import java.io.Serializable;
import java.util.Objects;

public class SaveRevenueModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String revenueDescription;

    private Double value;

    private String status;

    private Integer categoryId;

    public String getRevenueDescription() {
        return revenueDescription;
    }

    public void setRevenueDescription(String revenueDescription) {
        this.revenueDescription = revenueDescription;
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

        SaveRevenueModel that = (SaveRevenueModel) o;

        if (!Objects.equals(revenueDescription, that.revenueDescription))
            return false;
        if (!Objects.equals(value, that.value)) return false;
        if (!Objects.equals(status, that.status)) return false;
        return Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        int result = revenueDescription != null ? revenueDescription.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
