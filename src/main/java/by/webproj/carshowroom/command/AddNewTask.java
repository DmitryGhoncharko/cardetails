package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
public class AddNewTask  implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String cat = request.getParameter("cat");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        String date = request.getParameter("date");
        taskDao.create(desc,Integer.valueOf(price), Date.valueOf(date),Long.valueOf(cat));
        return requestFactory.createRedirectResponse("/controller?command=category");
    }
}
