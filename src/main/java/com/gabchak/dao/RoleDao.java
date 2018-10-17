package com.gabchak.dao;

import com.gabchak.model.Role;

public interface RoleDao extends GenericDao<Role, String> {

    Role findByName(String s);

    void deleteByName(String s);

}
