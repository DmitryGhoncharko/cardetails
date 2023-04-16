package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SimpleSavedDao implements SavedDao {
    private static final String SQL_SAVED_NAME_IS_EXIST = "select * from saved where saved_name = ?";
    private static final String SQL_GET_SAVED_NAME_BY_ID = "select saved_name where saved_id = ?";
    private static final String SQL_GET_ALL_SAVED = "select  saved_id, a.adition_id," + " adition_name, adition_weight, adition_count, b.body_id, body_name, body_weight, body_count," + " c.carengine_id, carengine_name, carengine_weight, carengine_count, s.salon_id, salon_name, salon_weight," + "salon_count, s2.separate_id, separate_name, separate_weight, separate_count, s3.suspension_id, suspension_name," + " suspension_weight, suspension_count, saved_name from saved" + " left join adition a on a.adition_id = saved.adition_id" + " left join body b on b.body_id = saved.body_id" + " left join carengine c on c.carengine_id = saved.carengine_id" + " left join salon s on s.salon_id = saved.salon_id" + " left join separate s2 on s2.separate_id = saved.separate_id" + " left join suspension s3 on s3.suspension_id = saved.suspension_id";
    private static final String SQL_GET_SAVED_BY_NAME = "select  saved_id, a.adition_id," + " adition_name, adition_weight, adition_count, b.body_id, body_name, body_weight, body_count," + " c.carengine_id, carengine_name, carengine_weight, carengine_count, s.salon_id, salon_name, salon_weight," + "salon_count, s2.separate_id, separate_name, separate_weight, separate_count, s3.suspension_id, suspension_name," + " suspension_weight, suspension_count, saved_name from saved" + " left join adition a on a.adition_id = saved.adition_id" + " left join body b on b.body_id = saved.body_id" + " left join carengine c on c.carengine_id = saved.carengine_id" + " left join salon s on s.salon_id = saved.salon_id" + " left join separate s2 on s2.separate_id = saved.separate_id" + " left join suspension s3 on s3.suspension_id = saved.suspension_id" + " where saved_name = ?";
    private static final String SQL_DELETE_SAVED_BY_NAME = "delete  from saved where saved_name = ?";
    private static final String SQL_DELETE_SAVED_BY_NAME_AND_ID = "delete  from saved where saved_name = ? AND saved_id = ?";

    private static final String SQL_UPDATE_SAVED_BY_NAME_AND_ID = "update saved set  adition_id = ?, body_id = ?, carengine_id = ?, salon_id = ?, separate_id = ?, suspension_id = ?, adition_count = ?, body_count = ?, carengine_count = ?, salon_count = ?, separate_count = ?, suspension_count = ? where saved_id = ? and saved_name = ?";
    private static final String SQL_CREATE_SAVED = "insert into saved(adition_id, body_id, carengine_id, salon_id, separate_id, suspension_id, adition_count, body_count, carengine_count, salon_count, separate_count, suspension_count, saved_name)  values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_GET_ALL_SAVED_NAMES = "select  distinct  saved_name from  saved";
    private final ConnectionPool connectionPool;

    @Override
    public List<SavedEntity> getAll() throws DaoException {
        List<SavedEntity> savedEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_SAVED);
            while (resultSet.next()) {
                SavedEntity savedEntity = createSavedEntityByResultSet(resultSet);
                savedEntities.add(savedEntity);
            }
        } catch (SQLException e) {
            log.error("Cannot find all saved entities", e);
            throw new DaoException("Cannot find all saved entities", e);
        }
        return savedEntities;
    }

    @Override
    public List<SavedEntity> getByName(String name) throws DaoException {
        List<SavedEntity> savedEntities = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_SAVED_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                SavedEntity savedEntity = createSavedEntityByResultSet(resultSet);
                savedEntities.add(savedEntity);
            }
        } catch (SQLException e) {
            log.error("Cannot find  saved entity by name", e);
            throw new DaoException("Cannot find  saved entity by name", e);
        }
        return savedEntities;
    }

    @Override
    public boolean deleteByName(String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SAVED_BY_NAME)) {
            preparedStatement.setString(1, name);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Cannot delete saved by name", e);
            throw new DaoException("Cannot delete saved by name", e);
        }
    }

    @Override
    public boolean deleteByNameAndId(String name, long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SAVED_BY_NAME_AND_ID)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Cannot delete saved by name and id", e);
            throw new DaoException("Cannot delete saved by name and id", e);
        }
    }

    @Override
    public boolean updateByNameAndId(SavedEntity savedEntity) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SAVED_BY_NAME_AND_ID)) {
            preparedStatement.setLong(1, savedEntity.getAdditionEntity() == null ? null : savedEntity.getAdditionEntity().getId());
            preparedStatement.setLong(2, savedEntity.getBodyEntity() == null ? null : savedEntity.getBodyEntity().getId());
            preparedStatement.setLong(3, savedEntity.getEngineEntity() == null ? null : savedEntity.getEngineEntity().getId());
            preparedStatement.setLong(4, savedEntity.getSalonEntity() == null ? null : savedEntity.getSalonEntity().getId());
            preparedStatement.setLong(5, savedEntity.getSeparaterlyEntity() == null ? null : savedEntity.getSeparaterlyEntity().getId());
            preparedStatement.setLong(6, savedEntity.getSuspensionEntity() == null ? null : savedEntity.getSuspensionEntity().getId());
            preparedStatement.setInt(7, savedEntity.getAdditionCount());
            preparedStatement.setInt(8, savedEntity.getBodyCount());
            preparedStatement.setInt(9, savedEntity.getEngineCount());
            preparedStatement.setInt(10, savedEntity.getSalonCount());
            preparedStatement.setInt(11, savedEntity.getSeparaterlyCount());
            preparedStatement.setInt(12, savedEntity.getSuspensionCount());
            preparedStatement.setLong(13, savedEntity.getId());
            preparedStatement.setString(14, savedEntity.getName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("Cannot update saved", e);
            throw new DaoException("Cannot update saved", e);
        }
    }

    @Override
    public void create(List<SavedEntity> savedEntities) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_SAVED)) {
            for (SavedEntity savedEntity : savedEntities) {
                preparedStatement.setLong(1, savedEntity.getAdditionEntity() == null ? null : savedEntity.getAdditionEntity().getId());
                preparedStatement.setLong(2, savedEntity.getBodyEntity() == null ? null : savedEntity.getBodyEntity().getId());
                preparedStatement.setLong(3, savedEntity.getEngineEntity() == null ? null : savedEntity.getEngineEntity().getId());
                preparedStatement.setLong(4, savedEntity.getSalonEntity() == null ? null : savedEntity.getSalonEntity().getId());
                preparedStatement.setLong(5, savedEntity.getSeparaterlyEntity() == null ? null : savedEntity.getSeparaterlyEntity().getId());
                preparedStatement.setLong(6, savedEntity.getSuspensionEntity() == null ? null : savedEntity.getSuspensionEntity().getId());
                preparedStatement.setInt(7, savedEntity.getAdditionCount());
                preparedStatement.setInt(8, savedEntity.getBodyCount());
                preparedStatement.setInt(9, savedEntity.getEngineCount());
                preparedStatement.setInt(10, savedEntity.getSalonCount());
                preparedStatement.setInt(11, savedEntity.getSeparaterlyCount());
                preparedStatement.setInt(12, savedEntity.getSuspensionCount());
                preparedStatement.setString(13, savedEntity.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Cannot create saved", e);
            throw new DaoException("Cannot create saved", e);
        }
    }

    @Override
    public Optional<String> getSavedNameById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_SAVED_NAME_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getString(1));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            log.error("Cannot find saved name by id", e);
            throw new DaoException("Cannot find saved name by id", e);
        }
    }

    @Override
    public boolean savedNameIsExist(String savedName) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVED_NAME_IS_EXIST)) {
            preparedStatement.setString(1, savedName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            log.error("Cannot find saved name is exist", e);
            throw new DaoException("Cannot find saved name is exist", e);
        }
    }

    @Override
    public List<String> getAllNames() throws DaoException {
       List<String> savedNames = new ArrayList<>();
        try(Connection connection= connectionPool.getConnection(); Statement statement = connection.createStatement()){
           ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_SAVED_NAMES);
           while (resultSet.next()){
               savedNames.add(resultSet.getString(1));
           }
       }catch (SQLException e){
           log.error("Cannot getAll saved names",e);
           throw new DaoException("Cannot getAll saved names",e);
       }
        return savedNames;
    }

    private SavedEntity createSavedEntityByResultSet(ResultSet resultSet) throws SQLException {
        return SavedEntity.builder().id(resultSet.getLong(1)).additionEntity(new AdditionEntity.Builder().withId(resultSet.getLong(2)).withName(resultSet.getString(3)).withWeight(resultSet.getDouble(4)).build()).additionCount(resultSet.getInt(5)).bodyEntity(new BodyEntity.Builder().withId(resultSet.getLong(6)).withName(resultSet.getString(7)).withWeight(resultSet.getDouble(8)).build()).bodyCount(resultSet.getInt(9)).engineEntity(new EngineEntity.Builder().withId(resultSet.getLong(10)).withName(resultSet.getString(11)).withWeight(resultSet.getDouble(12)).build()).engineCount(resultSet.getInt(13)).salonEntity(new SalonEntity.Builder().withId(resultSet.getLong(14)).withName(resultSet.getString(15)).withWeight(resultSet.getDouble(16)).build()).salonCount(resultSet.getInt(17)).separaterlyEntity(new SeparaterlyEntity.Builder().withId(resultSet.getLong(18)).withName(resultSet.getString(19)).withWeight(resultSet.getDouble(20)).build()).separaterlyCount(resultSet.getInt(21)).suspensionEntity(new SuspensionEntity.Builder().withId(resultSet.getLong(22)).withName(resultSet.getString(23)).withWeight(resultSet.getDouble(24)).build()).suspensionCount(resultSet.getInt(25)).name(resultSet.getString(26)).build();
    }
}
