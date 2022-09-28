package com.lucasdev3.crudbasicospring.dto;

import java.util.List;

public class CreateUserRoleDTO {

    private Integer idUser;

    private List<Integer> idsRoles;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<Integer> getIdsRoles() {
        return idsRoles;
    }

    public void setIdsRoles(List<Integer> idsRoles) {
        this.idsRoles = idsRoles;
    }


}
