package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateTestCommand implements Command{
    private final RequestFactory requestFactory;
    private final TestDao testDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testId = request.getParameter("testId");
        String testName = request.getParameter("testName");
        String testVisible = request.getParameter("testVisible");
        try {
            testDao.update(Long.valueOf(testId),testName,null == testVisible ? false : testVisible.equals("on"));
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return requestFactory.createRedirectResponse("/controller?command=editTest&test_id=" + testId);
    }
}
