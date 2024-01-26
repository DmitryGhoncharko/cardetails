package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Task;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.DataDao;
import by.webproj.carshowroom.model.dao.DtDao;
import by.webproj.carshowroom.model.dao.TaskDao;
import by.webproj.carshowroom.model.dao.UserDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubmitTaskCommand implements Command{
    private final RequestFactory requestFactory;
    private final DtDao dtDao;
    private final TaskDao taskDao;
    private final UserDao userDao;
    private final DataDao dataDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
            String chatId = request.getParameter("chatId");
            String text = request.getParameter("text");
            String status = request.getParameter("status");
            String taskId = request.getParameter("taskId");
            boolean flag = false;
            if(status!=null && status.equals("on")){
                flag = true;
            }
            if(flag){
                dtDao.update(chatId, Long.parseLong(taskId),flag);
                Task task = taskDao.getBytaskId(taskId);
                int balance = userDao.getUserBalance(chatId);
                userDao.update(chatId,balance + task.getPrice());
                dataDao.add(chatId + "_" + taskId,text);
            }else {
                Task task = taskDao.getBytaskId(taskId);
                dataDao.add(chatId + "_" + taskId,text);
                dtDao.delete(chatId, Long.parseLong(taskId));
            }

            return requestFactory.createRedirectResponse("/controller?command=taskRev");
    }
}
