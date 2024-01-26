package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteTaskCommand implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        taskDao.deleteById(Long.valueOf(id));
        return requestFactory.createRedirectResponse("/controller?command=command=category");
    }
}
