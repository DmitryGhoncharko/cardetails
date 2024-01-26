package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Dt;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DtDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_GET_ALL = "select chat_id, task_id from dt_ where flag_ = false";
    private static final String SQL_UPDATE = "update dt_ set flag_ = ? where  chat_id = ? and task_id = ?";
    public List<Dt> getAll(){
        List<Dt> dtList = new ArrayList<>();
        try(Connection connection =connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()){
                dtList.add(new Dt(resultSet.getString(1),resultSet.getLong(2)));
            }
        }catch (SQLException e){

        }
        return dtList;
    }
    public void update(String chatId, long taskId, boolean flag){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setBoolean(1,flag);
            preparedStatement.setString(2,chatId);
            preparedStatement.setLong(3,taskId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }
    public void delete(String chatId, long taskId){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from dt_ where chat_id = ? and task_id = ?")){
            preparedStatement.setString(1,chatId);
            preparedStatement.setLong(2,taskId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }
}
