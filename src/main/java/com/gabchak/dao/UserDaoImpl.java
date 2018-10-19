package com.gabchak.dao;

import com.gabchak.Factory;
import com.gabchak.model.QueryBuilder;
import com.gabchak.model.User;

import java.sql.*;

import static com.gabchak.model.Role.RoleName.USER;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    @Override
    public User addUser(User user) {
        String userQuery = Factory.getQueryBuilder().getInsertQuery(user.getClass());
        String roleQuery = "INSERT INTO USER_TO_ROLE (FK_USER_ID, FK_ROLE_ID) VALUES (?, ?)";
        PreparedStatement userStatement;
        PreparedStatement roleStatement;

        try {
            connection.setAutoCommit(false);
            userStatement = connection.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
            userStatement = prepareStatementForInsert(userStatement, user);
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
    public User findByToken(String token) {
        String query = "SELECT U.ID, U.EMAIL, U.TOKEN, U.PASSWORD, U.FIRST_NAME, U.LAST_NAME, R.NAME FROM USERS " +
                "JOIN USER_TO_ROLE UTR ON U.ID = UTR.FK_USER_ID " +
                "JOIN ROLES R ON UTR.FK_ROLE_ID = R.NAME " +
                "WHERE U.TOKEN = ?";
        return super.getObjectByParam(query, token);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT ID, EMAIL, TOKEN, PASSWORD, FIRST_NAME, LAST_NAME FROM USERS WHERE EMAIL = ?";
        return super.getObjectByParam(query, email);
    }

    @Override
    protected User getObjectFromResultSet(ResultSet resultSet) {
        User user = null;

        try {
            user = new User(
                    resultSet.getLong("ID"),
                    resultSet.getString("EMAIL"),
                    resultSet.getString("PASSWORD"),
                    resultSet.getString("FIRST_NAME"),
                    resultSet.getString("LAST_NAME"),
                    resultSet.getString("TOKEN")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected PreparedStatement prepareStatementForUpdate(PreparedStatement preparedStatement, User user) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getToken());
            preparedStatement.setLong(6, user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareStatementForInsert(PreparedStatement preparedStatement, User user) {
        try {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getToken());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
