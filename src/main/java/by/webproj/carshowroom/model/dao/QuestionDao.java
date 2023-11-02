package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.entity.QuestionEntity;
import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class QuestionDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_GET_BY_TEST_ID = "select question_id, question_text, question_rule, t.test_id, t.test_name,t.test_visible from _question " +
            "left join _test t on t.test_id = _question.test_id " +
            "where t.test_id = ?";
    private static final String SQL_UPDATE = "update _question set question_text = ? where question_id = ?";
    private static final String SQL_CREATE_QUESTION = "insert into _question(question_text,question_rule,test_id) values(?,?,?)";
    public List<QuestionEntity> getByTestId(Long id) throws DaoException {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_TEST_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                questionEntities.add(new QuestionEntity(resultSet.getLong("question_id"),
                        resultSet.getString("question_text"),resultSet.getString("question_rule"),
                        new TestEntity(resultSet.getLong("t.test_id"),resultSet.getString("t.test_name"),
                                resultSet.getBoolean("t.test_visible"))));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        return questionEntities;
    }
    public long createQuestion(String text, long testId) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_QUESTION, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,"Empty");
            preparedStatement.setLong(3,testId);
            int count = preparedStatement.executeUpdate();
            if(count>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return resultSet.getLong(1);
                }
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw  new DaoException();
        }
        throw new DaoException();
    }
    public void update(long questionId, String qiestionText){
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1,qiestionText);
            preparedStatement.setLong(2,questionId);
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
    public long getTestIdByQuestionId(long questionId){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select  test_id from _question where question_id = ?")){
            preparedStatement.setLong(1,questionId);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return -1;
    }

}
