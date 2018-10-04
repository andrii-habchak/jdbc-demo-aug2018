package com.gabchak.dao;

import com.gabchak.model.User;

public interface UserDao {


    public User addUser(User user);

    public User findByEmail(String email);
}
