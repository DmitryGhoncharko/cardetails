package by.webproj.carshowroom.command;

import by.webproj.carshowroom.command.dto.AnswerDto;
import by.webproj.carshowroom.command.dto.StartDto;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.AnswerEntity;
import by.webproj.carshowroom.entity.QuestionEntity;
import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.AnswerDao;
import by.webproj.carshowroom.model.dao.QuestionDao;
import by.webproj.carshowroom.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShowEditTestPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final TestDao testDao;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testId = request.getParameter("test_id");
        try {
            Optional<TestEntity> testEntity = testDao.getById(Long.valueOf(testId));
            TestEntity testEntity1 = testEntity.get();
            List<QuestionEntity> questionEntities = questionDao.getByTestId(Long.valueOf(testId));
            List<StartDto> startDtos = new ArrayList<>();
            for(QuestionEntity q : questionEntities){
                startDtos.add(new StartDto(new QuestionEntity(q.getId(),q.getText(),"Empty",testEntity1),answerDao.getAnswersByQuestionId(q.getId())));
            }
            request.addAttributeToJsp("data",startDtos);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return requestFactory.createForwardResponse(PagePath.EDIT_PAGE.getPath());
    }
}
