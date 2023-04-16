package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AddToSavedSessionCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String detailType = request.getParameter("type");
        String detailId = request.getParameter("id");
        String savedName = String.valueOf(request.retrieveFromSession("name").get());
        if (proceed(request, detailType, detailId)) {
            return requestFactory.createRedirectResponse("/controller?command=getSavedByName&rewrite=false&name=" + savedName);
        }
        return requestFactory.createRedirectResponse("/controller?command=getSavedByName&rewrite=false&name=" + savedName);
    }
    private boolean proceed(CommandRequest commandRequest, String detailType, String detailId) {
        switch (detailType) {
            case "engine": {
                return proceed(engineEntityCarDetailsService, detailId, commandRequest, "engine");
            }
            case "susp": {
                return proceed(suspensionEntityCarDetailsService, detailId, commandRequest, "susp");
            }
            case "body": {
                return proceed(bodyEntityCarDetailsService, detailId, commandRequest, "body");
            }
            case "salon": {
                return proceed(salonEntityCarDetailsService, detailId, commandRequest, "salon");
            }
            case "sep": {
                return proceed(separaterlyEntityCarDetailsService, detailId, commandRequest, "sep");
            }
            case "addition": {
                return proceed(additionEntityCarDetailsService, detailId, commandRequest, "addition");
            }
        }
        return false;
    }
    private boolean proceed(CarDetailsService<?> carDetailsService, String detailId, CommandRequest commandRequest, String type) {
        Optional<?> entity = carDetailsService.findById(Long.parseLong(detailId));
        if (entity.isPresent()) {
            switch (type) {
                case "engine": {
                    EngineEntity engine = (EngineEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("engineEntities");
                    if(objectOptional.isPresent()){
                        List<EngineEntity> list = (List<EngineEntity>) objectOptional.get();
                        list.add(engine);
                        commandRequest.removeFromSession("engineEntities");
                        commandRequest.addToSession("engineEntities",list);
                    }else {
                        List<EngineEntity> list = new ArrayList<>();
                        list.add(engine);
                        commandRequest.addToSession("engineEntities",list);
                    }
                    return true;

                }
                case "susp": {
                    SuspensionEntity suspension = (SuspensionEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("suspensionEntities");
                    if(objectOptional.isPresent()){
                        List<SuspensionEntity> list = (List<SuspensionEntity>) objectOptional.get();
                        list.add(suspension);
                        commandRequest.removeFromSession("suspensionEntities");
                        commandRequest.addToSession("suspensionEntities",list);
                    }else {
                        List<SuspensionEntity> list = new ArrayList<>();
                        list.add(suspension);
                        commandRequest.addToSession("suspensionEntities",list);
                    }
                    return true;
                }
                case "body": {
                    BodyEntity body = (BodyEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("bodyEntities");
                    if(objectOptional.isPresent()){
                        List<BodyEntity> list = (List<BodyEntity>) objectOptional.get();
                        list.add(body);
                        commandRequest.removeFromSession("bodyEntities");
                        commandRequest.addToSession("bodyEntities",list);
                    }else {
                        List<BodyEntity> list = new ArrayList<>();
                        list.add(body);
                        commandRequest.addToSession("bodyEntities",list);
                    }
                    return true;
                }
                case "salon": {
                    SalonEntity salon = (SalonEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("salonEntities");
                    if(objectOptional.isPresent()){
                        List<SalonEntity> list = (List<SalonEntity>) objectOptional.get();
                        list.add(salon);
                        commandRequest.removeFromSession("salonEntities");
                        commandRequest.addToSession("salonEntities",list);
                    }else {
                        List<SalonEntity> list = new ArrayList<>();
                        list.add(salon);
                        commandRequest.addToSession("salonEntities",list);
                    }
                    return true;
                }
                case "sep": {
                    SeparaterlyEntity separaterly = (SeparaterlyEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("separaterlyEntities");
                    if(objectOptional.isPresent()){
                        List<SeparaterlyEntity> list = (List<SeparaterlyEntity>) objectOptional.get();
                        list.add(separaterly);
                        commandRequest.removeFromSession("separaterlyEntities");
                        commandRequest.addToSession("separaterlyEntities",list);
                    }else {
                        List<SeparaterlyEntity> list = new ArrayList<>();
                        list.add(separaterly);
                        commandRequest.addToSession("separaterlyEntities",list);
                    }
                    return true;
                }
                case "addition": {
                    AdditionEntity addition = (AdditionEntity) entity.get();
                    Optional<Object> objectOptional = commandRequest.retrieveFromSession("additionEntities");
                    if(objectOptional.isPresent()){
                        List<AdditionEntity> list = (List<AdditionEntity>) objectOptional.get();
                        list.add(addition);
                        commandRequest.removeFromSession("additionEntities");
                        commandRequest.addToSession("additionEntities",list);
                    }else {
                        List<AdditionEntity> list = new ArrayList<>();
                        list.add(addition);
                        commandRequest.addToSession("additionEntities",list);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
