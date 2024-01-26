package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.Category;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CategoryDao {
    private final ConnectionPool connectionPool;

    public List<Category> getAll(){
        List<Category> categories = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select category_id, category_name from  category");
            while (resultSet.next()){
                categories.add(new Category(resultSet.getLong(1),resultSet.getString(2) ));
            }
        }catch (SQLException e){

        }
        return categories;
    }
    public void update(Long id, String name){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update category set category_name = ? where category_id = ?")){
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }
    public void deleteById(Long id){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from category where category_id = ?")){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){

        }
    }
    public Category getById(Long id){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement statement = connection.prepareStatement("select category_id, category_name from  category where category_id = ?")){
          statement.setLong(1,id);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()){
              return new Category(resultSet.getLong(1),resultSet.getString(2));
          }
        }catch (SQLException e){

        }
       return null;
    }
    public Category create(String name){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into category(category_name) values (?)",Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,name);
            int rows = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return new Category(resultSet.getLong(1),name);
            }
        }catch (SQLException e){

        }
        return null;
    }
}
