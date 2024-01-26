package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Category;
import by.webproj.carshowroom.entity.Task;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import com.mysql.cj.xdevapi.PreparableStatement;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class TaskDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_GET_BY_CATEGORY_ID = "select  task_id, task_description, task_price, date_end, category_id from task_ where category_id=?";
    private static final String SQL_GET_CREATE_TASK = "insert into task_(task_description, task_price, date_end, category_id) values(?,?,?,?)";
    private static final String  SQL_DELETE_BY_ID = "delete from task_ where task_id = ?";
    private static final String  SQL_UPDATE = "update task_ set task_description =? , task_price = ?, date_end = ? where task_id = ?";

    private static final String SQL_GET_BY_TASK_ID = "select  task_id, task_description, task_price, date_end, category_id from task_ where task_id=?";
    public List<Task> getByCategoryId(Long categoryId){
        List<Task> taskList = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_CATEGORY_ID)){
            preparedStatement.setLong(1,categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                taskList.add(new Task(resultSet.getLong(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4), new Category(resultSet.getLong(5),"")));
            }
        }catch (SQLException e){

        }
        return taskList;
    }
    public Task getBytaskId(String taskId){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_TASK_ID)){
            preparedStatement.setString(1,taskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
              return new Task(resultSet.getLong(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4), new Category(resultSet.getLong(5),""));
            }
        }catch (SQLException e){

        }
        return null;
    }
    public void deleteById(Long id){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }

    public void create(String description, int price, Date date, long categoryId){
        try(Connection connection =connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CREATE_TASK)){
            preparedStatement.setString(1,description);
            preparedStatement.setInt(2,price);
            preparedStatement.setDate(3,date);
            preparedStatement.setLong(4,categoryId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }

    public void update(long taskId, String desc, int price, Date date){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1,desc);
            preparedStatement.setInt(2,price);
            preparedStatement.setDate(3,date);
            preparedStatement.setLong(4,taskId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }

}
