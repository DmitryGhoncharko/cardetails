package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowCreateTaskPage implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String taskId = request.getParameter("id");
        request.addAttributeToJsp("data",taskId);
        return requestFactory.createForwardResponse(PagePath.CREATE_TASK.getPath());
    }
}
