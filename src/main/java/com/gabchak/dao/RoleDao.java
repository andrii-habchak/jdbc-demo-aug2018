package com.gabchak.dao;

import com.gabchak.model.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, String> {

    @Override
    void insert(Role role);

    Role findByName(String s);

    @Override
    void update(Role role);

    void deleteByName(String s);

    @Override
    List<Role> findAll();
}
