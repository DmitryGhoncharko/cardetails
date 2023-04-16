package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClearSessionSavedCommand implements Command{
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        request.removeFromSession("engineEntities");
        request.removeFromSession("suspensionEntities");
        request.removeFromSession("bodyEntities");
        request.removeFromSession("salonEntities");
        request.removeFromSession("separaterlyEntities");
        request.removeFromSession("additionEntities");
        String savedName = String.valueOf(request.retrieveFromSession("name").get());
        return requestFactory.createRedirectResponse("/controller?command=getSavedByName&rewrite=false&name=" + savedName);
    }
}
