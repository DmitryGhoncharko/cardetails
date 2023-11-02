package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.UserEntity;
import by.webproj.carshowroom.exception.DaoException;

import java.util.Optional;

public interface UserDao {

   Optional<UserEntity> findUserByLogin(String login) throws DaoException;

   boolean addUserAsClient(String login, String password) throws DaoException;
}
