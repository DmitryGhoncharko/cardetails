package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CategoryDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCategoryCommand implements Command{
    private final RequestFactory requestFactory;
    private final CategoryDao categoryDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        String  name = request.getParameter("name");
        categoryDao.update(Long.valueOf(id),name);
        return requestFactory.createRedirectResponse("/controller?command=category");
    }
}
