package com.gabchak.service;

import com.gabchak.dao.UserDao;
import com.gabchak.model.Role;
import com.gabchak.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import static com.gabchak.model.Role.RoleName.USER;

public class UserServiceImpl extends AbstractService<User, Long> implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    @Override
    public User addUser(User user) {
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        user.setToken(getToken());
        user.addRole(getDefaultRole());
        return userDao.addUser(user);
    }

    private Role getDefaultRole() {
        return Role.of(USER.toString());
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByToken(String token) {
        return userDao.findByToken(token);
    }

    @Override
    public boolean validatePassword(User user, String password) {
        boolean result = false;
        if (user != null) {
            String hashedPassword = hashPassword(password);
            result = hashedPassword.equals(user.getPassword());
        }
        return result;
    }

    private String getToken() {
        return UUID.randomUUID().toString();
    }

    private String hashPassword(String password) {
        MessageDigest digest;
        byte[] encodedHash = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedHash = digest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytesToHex(encodedHash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
