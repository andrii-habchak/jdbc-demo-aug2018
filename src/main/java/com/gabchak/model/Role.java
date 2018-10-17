package com.gabchak.model;

import com.gabchak.metaData.ColumnName;
import com.gabchak.metaData.TableName;

@TableName("ROLE")
public class Role {

    @ColumnName
    private RoleName roleName;
    private User user;


    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }
}
