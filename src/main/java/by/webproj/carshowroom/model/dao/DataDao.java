package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class DataDao {
    private final ConnectionPool connectionPool;

    public void add(String chatId, String message){
try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into  data_(chat_id, message_) values (?,?)")){
    preparedStatement.setString(1,chatId);
    preparedStatement.setString(2,message);
    preparedStatement.executeUpdate();
}catch (SQLException e){

}
    }
}
