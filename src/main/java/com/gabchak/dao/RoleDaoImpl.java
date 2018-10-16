package com.gabchak.dao;

import com.gabchak.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl extends AbstractDao<Role, String> implements RoleDao {

    protected RoleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Role findByName(String name) {
        return super.findById(name);
    }

    @Override
    public void deleteByName(String name) {
        super.deleteById(name);
    }

    @Override
    protected Role getObjectFromResultSet(ResultSet resultSet) {
        Role role = null;

        try {
            role = new Role((Role.RoleName) resultSet.getObject(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(PreparedStatement preparedStatement, Role role) {
        return null; //Name is ID. What to do?
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(PreparedStatement preparedStatement, Role role) {
        try {
            preparedStatement.setString(1, role.getRoleName().name());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }


}
