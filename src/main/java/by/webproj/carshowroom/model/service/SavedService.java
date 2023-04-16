package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.SavedEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;

import java.util.List;
import java.util.Optional;

public interface SavedService {
    List<SavedEntity> getAll() throws ServiceError;

    List<SavedEntity> getByName(String name) throws ServiceError;

    boolean deleteByName(String name)throws ServiceError;

    boolean updateByNameAndId(SavedEntity savedEntity) throws ServiceError;

    Optional<String> getSavedNameById(long id) throws ServiceError;

    boolean savedNameIsExist(String savedName) throws ServiceError;

    boolean deleteByNameAndId(String name, long id) throws ServiceError;

    void create(List<SavedEntity> savedEntities, String name) throws ServiceError;
    void createNew(List<SavedEntity> savedEntities) throws ServiceError;

    List<String> getAllNames() throws ServiceError;
}
