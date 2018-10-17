package com.gabchak.dao;

import com.gabchak.model.User;

public interface UserDao extends GenericDao<User, Long> {

    User addUser(User user);

    User findByEmail(String email);

    User findByToken(String token);

    void deleteById(Long id);

    void updateUser(User user);
}
