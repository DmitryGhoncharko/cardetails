package by.webproj.carshowroom.command;

import by.webproj.carshowroom.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditTestCommand implements Command{
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return null;
    }
}
