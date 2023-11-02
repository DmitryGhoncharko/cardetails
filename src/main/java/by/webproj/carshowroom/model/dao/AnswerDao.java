package by.webproj.carshowroom.model.dao;

import by.webproj.carshowroom.command.dto.AnswerDto;
import by.webproj.carshowroom.entity.AnswerEntity;
import by.webproj.carshowroom.entity.QuestionEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class AnswerDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_GET_ANSWERS_BY_QUESTION_ID = "select answer_id, answer_text, answer_true from _answer where question_id = ?";
    private static final String SQL_CREATE_ANSWER = "insert into _answer(answer_text,answer_true,question_id) values(?,?,?)";
    public List<AnswerDto> getAnswersByQuestionId(Long id) throws DaoException {
        List<AnswerDto> answerEntities = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ANSWERS_BY_QUESTION_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                answerEntities.add(new AnswerDto(resultSet.getLong("answer_id"),
                        resultSet.getString("answer_text"),
                        resultSet.getBoolean("answer_true")));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
            throw new DaoException();
        }
        return answerEntities;
    }
    public void createAnswer(String text, boolean answerTrue, long id){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement =connection.prepareStatement(SQL_CREATE_ANSWER)){
            preparedStatement.setString(1,text);
            preparedStatement.setBoolean(2,answerTrue);
            preparedStatement.setLong(3,id);
            int rows = preparedStatement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
    public void updateAnswer(long id, String text, boolean answerTrue){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update _answer set answer_text = ?, answer_true = ? where answer_id = ?")){
            preparedStatement.setString(1,text);
            preparedStatement.setBoolean(2,answerTrue);
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
    public long getQuestionIdByAnswerId(long answerId){
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select question_id from _answer where answer_id = ?")){
            preparedStatement.setLong(1,answerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong(1);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return -1;
    }
}
