package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
public class UpdateTaskCommand implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String id = request.getParameter("id");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        String date = request.getParameter("date");
        taskDao.update(Long.valueOf(id),desc,Integer.valueOf(price), Date.valueOf(date));
        return requestFactory.createRedirectResponse("/controller?command=category");
    }
}
