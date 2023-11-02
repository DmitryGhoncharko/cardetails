package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShowTestsAdminCommand implements Command{
    private final RequestFactory requestFactory;
    private final TestDao testDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        try {
            List<TestEntity> testEntities = testDao.getAll();
            request.addAttributeToJsp("tests",testEntities);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return requestFactory.createForwardResponse(PagePath.TEST_ADMIN_PAGE.getPath());
    }
}
