package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Category;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.connection.ConnectionPool;
import by.webproj.carshowroom.model.dao.CategoryDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowCategoryPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final CategoryDao categoryDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<Category> categoryList = categoryDao.getAll();
        request.addAttributeToJsp("data",categoryList);
        return requestFactory.createForwardResponse(PagePath.CATEGORY.getPath());
    }


}
