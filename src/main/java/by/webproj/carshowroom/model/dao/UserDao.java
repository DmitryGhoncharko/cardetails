package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class UserDao {
    private final ConnectionPool connectionPool;

    public void update(String chatId, int bal){
    try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update user_ set balance = ? where chat_id = ?")){
        preparedStatement.setInt(1,bal);
        preparedStatement.setString(2,chatId);
        preparedStatement.executeUpdate();
    }catch (SQLException e){

    }
    }
    public int getUserBalance(String chatId){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select balance from user_ where chat_id = ?")){
            preparedStatement.setString(1,chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e){

        }
        return 0;
    }
}
