package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.AnswerDao;
import by.webproj.carshowroom.model.dao.QuestionDao;
import by.webproj.carshowroom.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ShowAddNewQuestionPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDao testDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testId = request.getParameter("testId");
        try {
            Optional<TestEntity> testEntity = testDao.getById(Long.valueOf(testId));
            request.addAttributeToJsp("test",testEntity.get());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return requestFactory.createForwardResponse(PagePath.ADD_PAGE.getPath());
    }
}
