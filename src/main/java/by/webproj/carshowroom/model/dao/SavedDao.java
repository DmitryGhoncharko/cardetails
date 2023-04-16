package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.SavedEntity;
import by.webproj.carshowroom.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface SavedDao {
    List<SavedEntity> getAll() throws DaoException;

    List<SavedEntity> getByName(String name) throws DaoException;

    boolean deleteByName(String name)throws DaoException;

    boolean updateByNameAndId(SavedEntity savedEntity) throws DaoException;

    Optional<String> getSavedNameById(long id) throws DaoException;

    boolean savedNameIsExist(String savedName) throws DaoException;

    boolean deleteByNameAndId(String name, long id) throws DaoException;

    void create(List<SavedEntity> savedEntities) throws DaoException;

    List<String> getAllNames() throws DaoException;
}
