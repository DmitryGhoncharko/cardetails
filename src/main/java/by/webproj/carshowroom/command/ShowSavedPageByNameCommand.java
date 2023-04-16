package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.CarDetailsService;
import by.webproj.carshowroom.model.service.SavedService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShowSavedPageByNameCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarDetailsService<AdditionEntity> additionEntityCarDetailsService;
    private final CarDetailsService<BodyEntity> bodyEntityCarDetailsService;
    private final CarDetailsService<EngineEntity> engineEntityCarDetailsService;
    private final CarDetailsService<SalonEntity> salonEntityCarDetailsService;
    private final CarDetailsService<SeparaterlyEntity> separaterlyEntityCarDetailsService;
    private final CarDetailsService<SuspensionEntity> suspensionEntityCarDetailsService;

    private final SavedService savedService;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        request.addAttributeToJsp("additions",getAllAdditions());
        request.addAttributeToJsp("bodies",getAllBodies());
        request.addAttributeToJsp("engines",getAllEngines());
        request.addAttributeToJsp("salons",getAllSalons());
        request.addAttributeToJsp("separaters",getAllSeparaters());
        request.addAttributeToJsp("suspensions",getAllSuspensions());
        String savedName = request.getParameter("name");
        String rewriteSession = request.getParameter("rewrite");
        request.addToSession("name",savedName);
        List<SavedEntity> savedEntities = savedService.getByName(savedName);
        if(rewriteSession==null){
            savedSavedEntityInSession(savedEntities,request);
        }
        return requestFactory.createForwardResponse(PagePath.ONE_SAVED.getPath());
    }
    private List<AdditionEntity> getAllAdditions() {
        return additionEntityCarDetailsService.findAll();
    }

    private List<BodyEntity> getAllBodies() {
        return bodyEntityCarDetailsService.findAll();
    }

    private List<EngineEntity> getAllEngines() {
        return engineEntityCarDetailsService.findAll();
    }

    private List<SalonEntity> getAllSalons() {
        return salonEntityCarDetailsService.findAll();
    }

    private List<SeparaterlyEntity> getAllSeparaters() {
        return separaterlyEntityCarDetailsService.findAll();
    }

    private List<SuspensionEntity> getAllSuspensions() {
        return suspensionEntityCarDetailsService.findAll();
    }

    private void savedSavedEntityInSession(List<SavedEntity> savedEntities, CommandRequest request){
        List<AdditionEntity> additionEntities= new ArrayList<>();
        List<BodyEntity> bodyEntities = new ArrayList<>();
        List<EngineEntity> engineEntities= new ArrayList<>();
        List<SalonEntity> salonEntities = new ArrayList<>();
        List<SeparaterlyEntity> separaterlyEntities = new ArrayList<>();
        List<SuspensionEntity> suspensionEntities = new ArrayList<>();
        for(SavedEntity savedEntity : savedEntities){
            if(savedEntity.getAdditionEntity()!=null && savedEntity.getAdditionEntity().getId() != null && savedEntity.getAdditionEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getAdditionCount(); i++){
                    additionEntities.add(savedEntity.getAdditionEntity());
                }
            }
            if(savedEntity.getBodyEntity()!=null && savedEntity.getBodyEntity().getId() != null && savedEntity.getBodyEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getBodyCount(); i++){
                    bodyEntities.add(savedEntity.getBodyEntity());
                }
            }
            if(savedEntity.getEngineEntity()!=null && savedEntity.getEngineEntity().getId() != null && savedEntity.getEngineEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getEngineCount(); i++){
                    engineEntities.add(savedEntity.getEngineEntity());
                }
            }
            if(savedEntity.getSalonEntity()!=null && savedEntity.getEngineEntity().getId() != null && savedEntity.getEngineEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getSalonCount(); i++){
                    salonEntities.add(savedEntity.getSalonEntity());
                }
            }
            if(savedEntity.getSeparaterlyEntity()!=null && savedEntity.getEngineEntity().getId() != null && savedEntity.getEngineEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getSeparaterlyCount(); i++){
                    separaterlyEntities.add(savedEntity.getSeparaterlyEntity());
                }
            }
            if(savedEntity.getSuspensionEntity()!=null && savedEntity.getEngineEntity().getId() != null && savedEntity.getEngineEntity().getName() != null){
                for(int i =0 ; i<savedEntity.getSuspensionCount(); i++){
                    suspensionEntities.add(savedEntity.getSuspensionEntity());
                }
            }
            request.addToSession("engineEntities",engineEntities);
            request.addToSession("suspensionEntities",suspensionEntities);
            request.addToSession("bodyEntities",bodyEntities);
            request.addToSession("salonEntities",salonEntities);
            request.addToSession("additionEntities",additionEntities);
            request.addToSession("separaterlyEntities",separaterlyEntities);
        }
    }
}