package com.lucasdev3.crudbasicospring.utils;

import com.lucasdev3.crudbasicospring.entities.User;
import com.lucasdev3.crudbasicospring.enums.Regex;
import com.lucasdev3.crudbasicospring.models.SaveCategoryModel;
import com.lucasdev3.crudbasicospring.responsesmodels.ResponseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidationUtils {

    public static List<ResponseModel> validateCategoryModel(SaveCategoryModel categoryModel, User userFound) {

        List<ResponseModel> responseEntityList = new ArrayList<>();

        String name = categoryModel.getName();
        String typeCategory = categoryModel.getTypeCategory();

        if (!isValidName(name)) {
            ResponseModel rm = new ResponseModel();
            rm.setMessage(HttpStatus.CONFLICT);
            rm.setResponseDescription("Nome inválido!");
            rm.setContentBodyResponse(name);
            responseEntityList.add(rm);
        }
        if (!isValidTypeCategory(typeCategory)) {
            ResponseModel rm = new ResponseModel();
            rm.setMessage(HttpStatus.CONFLICT);
            rm.setResponseDescription("Tipo de categoria inválida!");
            rm.setContentBodyResponse(typeCategory);
            responseEntityList.add(rm);
        }
        if (!isValidUser(userFound)) {
            ResponseModel rm = new ResponseModel();
            rm.setMessage(HttpStatus.CONFLICT);
            rm.setResponseDescription("Usuário inválido!");
            rm.setContentBodyResponse(userFound.getId());
            responseEntityList.add(rm);
        }
        if (responseEntityList.size() > 0) {
            return responseEntityList;
        }
        return null;
    }

    private static Boolean isValidName(String name) {
        return !StringUtils.isEmpty(name);
    }

    private static Boolean isValidDescription(String description) {
        return !StringUtils.isEmpty(description);
    }

    private static Boolean isValidValue(Double value) {
        return !value.isNaN() && value >= 0;
    }

    private static Boolean isValidTypeCategory(String typeCategory) {
        if (StringUtils.isEmpty(typeCategory)) {
            return false;
        }
        return typeCategory.equals(Regex.EXPENSE.toString()) || typeCategory.equals(Regex.REVENUE.toString());
    }

    private static Boolean isValidUser(User user) {
        return !Objects.isNull(user);
    }

}
