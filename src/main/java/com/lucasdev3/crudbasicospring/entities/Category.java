package com.lucasdev3.crudbasicospring.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_CATEGORIAS")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String typeCategory;

//	@OneToMany(mappedBy = "category", targetEntity = Expense.class, cascade = CascadeType.ALL)
//	private List<Expense> expenses;
//
//	@OneToMany(mappedBy = "category", targetEntity = Revenue.class, cascade = CascadeType.ALL)
//	private List<Revenue> revenues;

	public Category() {
	}
	
	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCategory() {
		return typeCategory;
	}

	public void setTypeCategory(String typeCategory) {
		this.typeCategory = typeCategory;
	}

//	public List<Expense> getExpenses() {
//		return expenses;
//	}
//
//	public void setExpenses(List<Expense> expenses) {
//		this.expenses = expenses;
//	}
//
//	public List<Revenue> getRevenues() {
//		return revenues;
//	}
//
//	public void setRevenues(List<Revenue> revenues) {
//		this.revenues = revenues;
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Category category = (Category) o;
//
//		if (!Objects.equals(id, category.id)) return false;
//		if (!Objects.equals(name, category.name)) return false;
//		if (!Objects.equals(typeCategory, category.typeCategory))
//			return false;
//		if (!Objects.equals(expenses, category.expenses)) return false;
//		return Objects.equals(revenues, category.revenues);
//	}
//
//	@Override
//	public int hashCode() {
//		int result = id != null ? id.hashCode() : 0;
//		result = 31 * result + (name != null ? name.hashCode() : 0);
//		result = 31 * result + (typeCategory != null ? typeCategory.hashCode() : 0);
//		result = 31 * result + (expenses != null ? expenses.hashCode() : 0);
//		result = 31 * result + (revenues != null ? revenues.hashCode() : 0);
//		return result;
//	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Category category = (Category) o;

		if (!Objects.equals(id, category.id)) return false;
		if (!Objects.equals(name, category.name)) return false;
		return Objects.equals(typeCategory, category.typeCategory);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (typeCategory != null ? typeCategory.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", typeCategory='" + typeCategory + '\'' +
				'}';
	}
}
