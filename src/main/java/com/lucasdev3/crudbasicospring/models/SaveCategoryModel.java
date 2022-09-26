package com.lucasdev3.crudbasicospring.models;

import java.io.Serializable;
import java.util.Objects;

public class SaveCategoryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;

	private String typeCategory;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SaveCategoryModel that = (SaveCategoryModel) o;

		if (!Objects.equals(name, that.name)) return false;
		return Objects.equals(typeCategory, that.typeCategory);
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (typeCategory != null ? typeCategory.hashCode() : 0);
		return result;
	}
}
