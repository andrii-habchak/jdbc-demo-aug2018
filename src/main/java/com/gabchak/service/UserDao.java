package com.gabchak.service;

import com.gabchak.dao.AbstractDao;
import com.gabchak.model.User;

public interface UserDao {


    public User addUser(User user);

    public User findByEmail(String email);
}
