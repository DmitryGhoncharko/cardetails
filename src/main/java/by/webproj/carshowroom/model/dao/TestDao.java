package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class TestDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_CREATE_TEST = "insert into  _test(test_name, test_visible) values (?,?)";
    private static final String SQL_UPDATE_TEST = "update _test set  test_name = ?, test_visible = ? where test_id = ?";
    private static final String  SQL_GET_ALL_TESTS = "select  test_id,test_name,test_visible from  _test";
    private static final String SQL_GET_ALL_TEST_VISIBLE = "select  test_id,test_name,test_visible from  _test where test_visible = true";
    private static final String SQL_GET_BY_ID_TEST = "select  test_id,test_name,test_visible from  _test where test_id = ?";
    public TestEntity create(String name, boolean visible) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TEST, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,name);
            preparedStatement.setBoolean(2,visible);
            int countRows = preparedStatement.executeUpdate();
            if(countRows>0){
                ResultSet resultSet =  preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return new TestEntity(resultSet.getLong(1),name,visible);
                }
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        log.error("Cannot create TestEntity in DAO");
        throw new DaoException();
    }
    public TestEntity update(Long id, String name, boolean visible) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TEST)){
            preparedStatement.setString(1,name);
            preparedStatement.setBoolean(2,visible);
            preparedStatement.setLong(3,id);
            int countRows = preparedStatement.executeUpdate();
            if(countRows>0){
                return new TestEntity(id,name,visible);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        log.error("Cannot update TestEntity In Dao");
        throw new DaoException();
    }
    public List<TestEntity> getAll() throws DaoException {
        List<TestEntity> testEntities = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TESTS);
            while (resultSet.next()){
                testEntities.add(new TestEntity(resultSet.getLong(1),resultSet.getString(2),resultSet.getBoolean(3)));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        return testEntities;
    }
    public List<TestEntity> getAllVisible() throws DaoException {
        List<TestEntity> testEntities = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TEST_VISIBLE);
            while (resultSet.next()){
                testEntities.add(new TestEntity(resultSet.getLong(1),resultSet.getString(2),resultSet.getBoolean(3)));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        return testEntities;
    }
    public Optional<TestEntity> getById(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID_TEST)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new TestEntity(resultSet.getLong(1),resultSet.getString(2),resultSet.getBoolean(3)));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        return Optional.empty();
    }

}
