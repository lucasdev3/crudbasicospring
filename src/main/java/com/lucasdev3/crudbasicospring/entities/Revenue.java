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

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String status;

    @JoinColumn(name = "category_id", nullable = false, table = "TB_CATEGORIAS")
    private Category category;

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

        Revenue revenue = (Revenue) o;

        if (!Objects.equals(id, revenue.id)) return false;
        if (!Objects.equals(revenueDescription, revenue.revenueDescription))
            return false;
        if (!Objects.equals(value, revenue.value)) return false;
        if (!Objects.equals(status, revenue.status)) return false;
        return Objects.equals(category, revenue.category);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (revenueDescription != null ? revenueDescription.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Revenue{" +
                "id=" + id +
                ", revenueDescription='" + revenueDescription + '\'' +
                ", value=" + value +
                ", status='" + status + '\'' +
                ", category=" + category +
                '}';
    }
}
