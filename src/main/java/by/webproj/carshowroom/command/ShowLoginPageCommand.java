package by.webproj.carshowroom.command;

import by.webproj.carshowroom.command.Command;
import by.webproj.carshowroom.command.CommandRequest;
import by.webproj.carshowroom.command.CommandResponse;
import by.webproj.carshowroom.command.PagePath;
import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ShowLoginPageCommand implements Command {
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
    }
}
