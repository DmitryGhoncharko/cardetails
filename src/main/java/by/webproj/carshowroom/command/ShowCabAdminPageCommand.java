package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.dao.SimpleUserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ShowCabAdminPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final SimpleUserDao simpleUserDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        Long countUsers = simpleUserDao.findCountUsers();
        request.addAttributeToJsp("usr",countUsers);
        return requestFactory.createForwardResponse(PagePath.ADMIN_CAB.getPath());
    }
}
