package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.TestEntity;
import by.webproj.carshowroom.exception.DaoException;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TestDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ShowTestPageCommand implements Command{
    private final TestDao testDao;
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        try {
            List<TestEntity> testEntities = testDao.getAllVisible();
            request.addAttributeToJsp("tests",testEntities);
            return requestFactory.createForwardResponse(PagePath.TEST_PAGE.getPath());
        } catch (DaoException e) {
            log.error(e.getMessage());
            throw new ServiceError();
        }

    }
}
