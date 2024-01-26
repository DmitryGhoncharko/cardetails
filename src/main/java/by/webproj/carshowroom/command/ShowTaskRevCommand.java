package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.Dt;
import by.webproj.carshowroom.entity.RevDto;
import by.webproj.carshowroom.entity.Task;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.CategoryDao;
import by.webproj.carshowroom.model.dao.DtDao;
import by.webproj.carshowroom.model.dao.TaskDao;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShowTaskRevCommand implements Command{
    private final RequestFactory requestFactory;
    private final TaskDao taskDao;
    private final CategoryDao categoryDao;
    private final DtDao dtDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<Dt> dtList = dtDao.getAll();
        List<RevDto> revDtoList = new ArrayList<>();
        for(Dt dt : dtList){
           Task task = taskDao.getBytaskId(String.valueOf(dt.getTaskId()));
           if(task!=null){
               revDtoList.add(new RevDto(task,dt.getChatId(),"http://195.133.49.99:8081/photo/" + dt.getChatId() + "_" + dt.getTaskId() + ".jpg" ));
           }
        }
        request.addAttributeToJsp("data",revDtoList);
        return requestFactory.createForwardResponse(PagePath.TASK_REV.getPath());
    }
}
