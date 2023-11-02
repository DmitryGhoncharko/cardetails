package by.webproj.carshowroom.model.dao;


import by.webproj.carshowroom.entity.Role;
import by.webproj.carshowroom.entity.UserEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

@RequiredArgsConstructor
public class SimpleUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_FIND_USER_BY_LOGIN = "select user_id, user_login, user_password, user_role_id  from  _user " + "where user_login = ?";

    private static final String SQL_ADD_USER = "insert into _user(user_login, user_password, user_role_id) values(?,?,?)";
    private static final String SQL_FIND_COUNT_USERS = "select count(user_id) from _user";
    private final ConnectionPool connectionPool;


    @Override
    public Optional<UserEntity> findUserByLogin(String login) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(UserEntity.builder().id(resultSet.getLong(1)).login(resultSet.getString(2)).password(resultSet.getString(3)).role(Role.INITIAL.getRoleById(resultSet.getLong(4))).build());
            }
        } catch (SQLException sqlException) {
            LOG.error("Cannot find user by login, login: " + login, sqlException);
            throw new DaoException("Cannot find user by login, login: " + login, sqlException);
        }
        LOG.error("Cannot find user by login, login: " + login);
        return Optional.empty();
    }
    public long findCountUsers(){
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_FIND_COUNT_USERS);
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (SQLException e){
            LOG.error(e.getMessage());
        }
        return 0;
    }
    @Override
    public boolean addUserAsClient(String login, String password) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setLong(3,Role.CLIENT.getRoleId());
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            LOG.error("Cannot add user userLogin = " + login + " userPassord =  " + password);
            throw new DaoException("Cannot add user userLogin = " + login + " userPassord =  " + password);
        }
    }
}
