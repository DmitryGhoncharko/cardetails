package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.AnswerDao;
import by.webproj.carshowroom.model.dao.QuestionDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddQuestionComand implements Command{
    private final RequestFactory requestFactory;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {

        String testId = request.getParameter("testId");
        String question1 = request.getParameter("question1");
        String answerText1 = request.getParameter("answerText1");
        String answerTextTrue1 =request.getParameter("answerTextTrue1");
        String answerText2 = request.getParameter("answerText2");
        String answerTextTrue2 =request.getParameter("answerTextTrue2");
        String answerText3 = request.getParameter("answerText3");
        String answerTextTrue3 =request.getParameter("answerTextTrue3");
        String answerText4 = request.getParameter("answerText4");
        String answerTextTrue4 =request.getParameter("answerTextTrue4");
        long questionId = 0;
        try {
            questionId = questionDao.createQuestion(question1, Long.parseLong(testId));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        answerDao.createAnswer(answerText1, null == answerTextTrue1 ? false : answerTextTrue1.equals("on"),questionId);
        answerDao.createAnswer(answerText2, null == answerTextTrue2 ? false : answerTextTrue2.equals("on"),questionId);
        answerDao.createAnswer(answerText3, null == answerTextTrue3 ? false : answerTextTrue3.equals("on"),questionId);
        answerDao.createAnswer(answerText4, null == answerTextTrue4 ? false : answerTextTrue4.equals("on"),questionId);

        return requestFactory.createRedirectResponse("/controller?command=editTest&test_id=" + testId);
    }
}
