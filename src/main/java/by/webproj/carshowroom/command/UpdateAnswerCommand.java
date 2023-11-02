package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.AnswerDao;
import by.webproj.carshowroom.model.dao.QuestionDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateAnswerCommand implements Command{
    private final RequestFactory requestFactory;
    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String answerId = request.getParameter("answerId");
        String answerText = request.getParameter("answerText");
        String answerTrue = request.getParameter("answerTrue");
        answerDao.updateAnswer(Long.parseLong(answerId),answerText,null == answerTrue ? false : answerTrue.equals("on"));
        long questionId = answerDao.getQuestionIdByAnswerId(Long.parseLong(answerId));
        long testId = questionDao.getTestIdByQuestionId(questionId);
        return requestFactory.createRedirectResponse("/controller?command=editTest&test_id=" + testId);
    }
}
