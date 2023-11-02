package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.QuestionDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateQuestionCommand implements Command{
    private final RequestFactory requestFactory;
    private final QuestionDao questionDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String questionId = request.getParameter("questionId");
        String questionText = request.getParameter("questionText");
        questionDao.update(Long.parseLong(questionId),questionText);
        long id = questionDao.getTestIdByQuestionId(Long.parseLong(questionId));
        return requestFactory.createRedirectResponse("/controller?command=editTest&test_id=" + id);
    }
}
