package by.webproj.carshowroom.command;

import by.webproj.carshowroom.command.dto.AnswerDto;
import by.webproj.carshowroom.command.dto.StartDto;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.QuestionEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.AnswerDao;
import by.webproj.carshowroom.model.dao.QuestionDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class StartTestCommand implements Command{
    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testId = request.getParameter("test_id");
        List<StartDto> startDtos = new ArrayList<>();
        try {
           List<QuestionEntity> questionEntities = questionDao.getByTestId(Long.valueOf(testId));
           for(QuestionEntity questionEntity : questionEntities){
               List<AnswerDto> answerDtos = answerDao.getAnswersByQuestionId(questionEntity.getId());
               startDtos.add(new StartDto(questionEntity,answerDtos));
           }
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceError();
        }
        request.addAttributeToJsp("data",startDtos);
        return requestFactory.createForwardResponse(PagePath.START_PAGE.getPath());
    }
}
