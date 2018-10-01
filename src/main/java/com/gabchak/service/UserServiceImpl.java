package com.gabchak.service;

import com.gabchak.dao.AbstractDao;
import com.gabchak.model.User;

import java.util.UUID;

public class UserServiceImpl extends AbstractDao<User, Long> implements UserService {
    private final UserDao userDao;

    @Override
    public User addUser(User user) {
        user.setToken(getToken());
        return userDao.addUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean validatePassword(User user, String password) {

        return false;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }
}
