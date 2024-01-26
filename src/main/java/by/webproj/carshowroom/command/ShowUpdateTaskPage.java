package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Task;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowUpdateTaskPage implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        Task task = taskDao.getBytaskId(request.getParameter("id"));
        request.addAttributeToJsp("data",task);
        return requestFactory.createForwardResponse(PagePath.UPDATE_TASK.getPath());
    }
}
