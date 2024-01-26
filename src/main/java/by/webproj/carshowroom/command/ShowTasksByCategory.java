package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Task;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowTasksByCategory implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        List<Task> taskList = taskDao.getByCategoryId(Long.valueOf(id));
        request.addAttributeToJsp("data",taskList);
        request.addAttributeToJsp("ctId",id);
        return requestFactory.createForwardResponse(PagePath.TASK_PAGE.getPath());
    }
}
