package by.webproj.carshowroom.model.service;

import by.webproj.carshowroom.entity.SavedEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.SavedDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class SimpleSavedService implements SavedService {
    private static final String ERROR_MESSAGE = "Cannot complete operation in service";
    private final SavedDao savedDao;

    @Override
    public List<SavedEntity> getAll() throws ServiceError {
        try {
            return savedDao.getAll();
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public List<SavedEntity> getByName(String name) throws ServiceError {
        try {
            return savedDao.getByName(name);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean deleteByName(String name) throws ServiceError {
        try {
            return savedDao.deleteByName(name);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean updateByNameAndId(SavedEntity savedEntity) throws ServiceError {
        try {
            return savedDao.updateByNameAndId(savedEntity);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public Optional<String> getSavedNameById(long id) throws ServiceError {
        try {
            return savedDao.getSavedNameById(id);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean savedNameIsExist(String savedName) throws ServiceError {
        try {
            return savedDao.savedNameIsExist(savedName);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public boolean deleteByNameAndId(String name, long id) throws ServiceError {
        try {
            return savedDao.deleteByNameAndId(name, id);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public void create(List<SavedEntity> savedEntities, String name) throws ServiceError {
        try {
            savedDao.deleteByName(name);
            savedDao.create(savedEntities);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public void createNew(List<SavedEntity> savedEntities) throws ServiceError {
        try {
            savedDao.create(savedEntities);
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }

    @Override
    public List<String> getAllNames() throws ServiceError {
        try {
            return savedDao.getAllNames();
        } catch (DaoException e) {
            log.error(ERROR_MESSAGE, e);
            throw new ServiceError(ERROR_MESSAGE, e);
        }
    }
}
