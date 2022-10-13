package com.lucasdev3.crudbasicospring.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_REVENUE")
public class Revenue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String revenueDescription;

    @Column(nullable = false, precision = 11, scale = 2)
    private Double value;

    @Column(nullable = false, length = 15)
    private String status;

    @JoinColumn(name = "category_id", nullable = false, table = "TB_CATEGORIAS")
    private Category categoryRevenue;

    @JoinColumn(name = "id", nullable = false, table = "TB_USERS")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Category getCategoryRevenue() {
        return categoryRevenue;
    }

    public void setCategoryRevenue(Category categoryRevenue) {
        this.categoryRevenue = categoryRevenue;
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

        Revenue revenue = (Revenue) o;

        if (!Objects.equals(id, revenue.id)) return false;
        if (!Objects.equals(revenueDescription, revenue.revenueDescription))
            return false;
        if (!Objects.equals(value, revenue.value)) return false;
        if (!Objects.equals(status, revenue.status)) return false;
        if (!Objects.equals(categoryRevenue, revenue.categoryRevenue))
            return false;
        return Objects.equals(user, revenue.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (revenueDescription != null ? revenueDescription.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (categoryRevenue != null ? categoryRevenue.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
