package com.gabchak.service;


import com.gabchak.model.User;

public interface UserService extends Service<User, Long> {

    User addUser(User user);

    User findByEmail(String email);

    boolean validatePassword(User user, String password);

    User findByToken(String token);
}
