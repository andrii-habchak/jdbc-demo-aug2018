package com.gabchak.dao;

import com.gabchak.model.User;

import java.sql.*;
import static com.gabchak.model.Role.RoleName.USER;

public class UserDaoImpl implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User addUser(User user) {
        String userQuery = "INSERT INTO USERS (EMAIL, TOKEN, PASSWORD, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)";
        String roleQuery = "INSERT INTO USER_TO_ROLE (FK_USER_ID, FK_ROLE_ID) VALUES (?, ?)";
        PreparedStatement userStatement;
        PreparedStatement roleStatement;

        try {
            connection.setAutoCommit(false);
            userStatement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userStatement.setString(1, user.getEmail());
            userStatement.setString(2, user.getToken());
            userStatement.setString(3, user.getPassword());
            userStatement.setString(4, user.getFirstName());
            userStatement.setString(5, user.getLastName());
            userStatement.executeUpdate();

            ResultSet resultSet = userStatement.getGeneratedKeys();
            long userId = 0;
            if (resultSet.next()) {
                userId = resultSet.getLong(1);
            } else {
                connection.rollback();
            }

            roleStatement = connection.prepareStatement(roleQuery);
            roleStatement.setLong(1, userId);
            roleStatement.setString(2, USER.toString());
            roleStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT ID, EMAIL, TOKEN, PASSWORD, FIRST_NAME, LAST_NAME FROM USERS WHERE EMAIL = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        User user = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            user = resultSet.next() ? getUser(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByToken(String token) {
        String query = "SELECT ID, EMAIL, TOKEN, PASSWORD, FIRST_NAME, LAST_NAME FROM USERS WHERE TOKEN = ?";
        PreparedStatement statement;
        ResultSet resultSet;
        User user = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, token);
            resultSet = statement.executeQuery();
            user = resultSet.next() ? getUser(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("ID"),
                resultSet.getString("EMAIL"),
                resultSet.getString("PASSWORD"),
                resultSet.getString("FIRST_NAME"),
                resultSet.getString("LAST_NAME"),
                resultSet.getString("TOKEN")
        );
    }
}
