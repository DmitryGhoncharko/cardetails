package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.UserEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;

import by.webproj.carshowroom.model.dao.UserDao;
import by.webproj.carshowroom.securiy.PasswordHasher;
import by.webproj.carshowroom.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
@RequiredArgsConstructor
public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class);
    private final UserValidator userValidator;
    private final UserDao userDao;
    private final PasswordHasher passwordHasher;


    @Override
    public Optional<UserEntity> loginUser(String login, String password) {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return Optional.empty();
        }
        try {

            final Optional<UserEntity> userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()) {
                final UserEntity userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, hashedPasswordFromDB)) {
                    return userFromDB;
                }
            }
        } catch (DaoException daoException) {
            LOG.error("Cannot authorize user, userLogin: " + login + " userPassword :" + password, daoException);
            throw new ServiceError("Cannot authorize user, userLogin: " + login + " userPassword :" + password);
        }
        return Optional.empty();
    }

    @Override
    public boolean registrationUser(String login, String password) {
        if(!userValidator.validateUserDataByLoginAndPassword(login,password)){
            return false;
        }
        try{
            final  String hashedPassword = passwordHasher.hashPassword(password);
            return  userDao.addUser(login,hashedPassword);
        }catch (DaoException e ){
            LOG.error("Cannot add user by login and password, login = "  + login + " password = "  + password);
            throw new ServiceError("Cannot add user by login and password, login = "  + login + " password = "  + password);
        }
    }
}
