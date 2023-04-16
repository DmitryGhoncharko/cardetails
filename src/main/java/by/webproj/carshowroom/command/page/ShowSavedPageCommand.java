package by.webproj.carshowroom.command.page;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.command.PagePath;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.SavedService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowSavedPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final SavedService savedService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<String> savedNames = savedService.getAllNames();
        request.addAttributeToJsp("savedNames", savedNames);
        return requestFactory.createForwardResponse(PagePath.SAVED.getPath());
    }
}
