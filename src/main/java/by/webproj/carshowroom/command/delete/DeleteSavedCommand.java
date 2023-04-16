package by.webproj.carshowroom.command.delete;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.SavedService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteSavedCommand implements Command {
    private final RequestFactory requestFactory;
    private final SavedService savedService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String savedName = request.getParameter("name");
        savedService.deleteByName(savedName);
        return requestFactory.createRedirectResponse("/controller?command=saved");
    }
}
