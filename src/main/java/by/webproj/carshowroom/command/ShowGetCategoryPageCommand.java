package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Category;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CategoryDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowGetCategoryPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final CategoryDao categoryDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        Category category = categoryDao.getById(Long.valueOf(id));
        request.addAttributeToJsp("data",category);
        request.addAttributeToJsp("ctId",id);
        return requestFactory.createForwardResponse(PagePath.UPDATE_CATEGORY.getPath());
    }
}
