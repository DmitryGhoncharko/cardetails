package by.webproj.carshowroom.command;

import by.webproj.carshowroom.controller.RequestFactory;
import by.webproj.carshowroom.entity.*;
import by.webproj.carshowroom.exception.ServiceError;
import by.webproj.carshowroom.model.service.SavedService;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class SaveSavedCommand implements Command {
    private final RequestFactory requestFactory;
    private final SavedService savedService;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String savedName = String.valueOf(request.retrieveFromSession("name").get());
        Optional<Object> objectOptional = request.retrieveFromSession("engineEntities");
        List<EngineEntity> engineEntities = (List<EngineEntity>) objectOptional.get();
        Optional<Object> objectOptional1 = request.retrieveFromSession("suspensionEntities");
        List<SuspensionEntity> suspensionEntities = (List<SuspensionEntity>) objectOptional1.get();
        Optional<Object> objectOptional2 = request.retrieveFromSession("bodyEntities");
        List<BodyEntity> bodyEntities = (List<BodyEntity>) objectOptional2.get();
        Optional<Object> objectOptional3 = request.retrieveFromSession("salonEntities");
        List<SalonEntity> salonEntities = (List<SalonEntity>) objectOptional3.get();
        Optional<Object> objectOptional4 = request.retrieveFromSession("separaterlyEntities");
        List<SeparaterlyEntity> separaterlyEntities = (List<SeparaterlyEntity>) objectOptional4.get();
        Optional<Object> objectOptional5 = request.retrieveFromSession("additionEntities");
        List<AdditionEntity> additionEntities = (List<AdditionEntity>) objectOptional5.get();
        List<SavedEntity> savedEntities = createSavedEntityList(engineEntities,suspensionEntities,bodyEntities,salonEntities,separaterlyEntities,additionEntities,savedName);
        savedService.create(savedEntities,savedName);
        return requestFactory.createRedirectResponse("/controller?command=saved");
    }

    private Map<EngineEntity, Integer> mapEngines(List<EngineEntity> engineEntities) {
        Map<EngineEntity, Integer> map = new HashMap<>();
        for (EngineEntity engine : engineEntities) {
            if (map.containsKey(engine)) {
                Integer count = map.get(engine);
                map.put(engine, ++count);
            } else {
                map.put(engine, 1);
            }
        }
        return map;
    }

    private Map<SuspensionEntity, Integer> mapSuspension(List<SuspensionEntity> suspensionEntities) {
        Map<SuspensionEntity, Integer> map = new HashMap<>();
        for (SuspensionEntity suspension : suspensionEntities) {
            if (map.containsKey(suspension)) {
                Integer count = map.get(suspension);
                map.put(suspension, ++count);
            } else {
                map.put(suspension, 1);
            }
        }
        return map;
    }

    private Map<BodyEntity, Integer> mapBody(List<BodyEntity> bodyEntities) {
        Map<BodyEntity, Integer> map = new HashMap<>();
        for (BodyEntity bodyEntity : bodyEntities) {
            if (map.containsKey(bodyEntity)) {
                Integer count = map.get(bodyEntity);
                map.put(bodyEntity, ++count);
            } else {
                map.put(bodyEntity, 1);
            }
        }
        return map;
    }

    private Map<SalonEntity, Integer> mapSalon(List<SalonEntity> salonEntities) {
        Map<SalonEntity, Integer> map = new HashMap<>();
        for (SalonEntity salon : salonEntities) {
            if (map.containsKey(salon)) {
                Integer count = map.get(salon);
                map.put(salon, ++count);
            } else {
                map.put(salon, 1);
            }
        }
        return map;
    }

    private Map<SeparaterlyEntity, Integer> mapSeparaterly(List<SeparaterlyEntity> separaterlyEntities) {
        Map<SeparaterlyEntity, Integer> map = new HashMap<>();
        for (SeparaterlyEntity separaterly : separaterlyEntities) {
            if (map.containsKey(separaterly)) {
                Integer count = map.get(separaterly);
                map.put(separaterly, ++count);
            } else {
                map.put(separaterly, 1);
            }
        }
        return map;
    }

    private Map<AdditionEntity, Integer> mapAdditions(List<AdditionEntity> additionEntities) {
        Map<AdditionEntity, Integer> map = new HashMap<>();
        for (AdditionEntity addition : additionEntities) {
            if (map.containsKey(addition)) {
                Integer count = map.get(addition);
                map.put(addition, ++count);
            } else {
                map.put(addition, 1);
            }
        }
        return map;
    }

    private List<SavedEntity> createSavedEntityList(List<EngineEntity> engineEntities, List<SuspensionEntity> suspensionEntities, List<BodyEntity> bodyEntities, List<SalonEntity> salonEntities, List<SeparaterlyEntity> separaterlyEntities, List<AdditionEntity> additionEntities, String name) {
        List<SavedEntity> savedEntities = new ArrayList<>();
        Map<EngineEntity, Integer> engineEntityIntegerMap = mapEngines(engineEntities);
        Map<SuspensionEntity, Integer> suspensionEntityIntegerMap = mapSuspension(suspensionEntities);
        Map<BodyEntity, Integer> bodyEntityIntegerMap = mapBody(bodyEntities);
        Map<SalonEntity, Integer> salonEntityIntegerMap = mapSalon(salonEntities);
        Map<SeparaterlyEntity, Integer> separaterlyEntityIntegerMap = mapSeparaterly(separaterlyEntities);
        Map<AdditionEntity, Integer> additionEntityIntegerMap = mapAdditions(additionEntities);

        Set<Map.Entry<EngineEntity, Integer>> engineEntry = engineEntityIntegerMap.entrySet();
        Set<Map.Entry<SuspensionEntity, Integer>> suspEntry = suspensionEntityIntegerMap.entrySet();
        Set<Map.Entry<BodyEntity, Integer>> bodyEntry = bodyEntityIntegerMap.entrySet();
        Set<Map.Entry<SalonEntity, Integer>> salonEntry = salonEntityIntegerMap.entrySet();
        Set<Map.Entry<SeparaterlyEntity, Integer>> sepEntry = separaterlyEntityIntegerMap.entrySet();
        Set<Map.Entry<AdditionEntity, Integer>> additionEntry = additionEntityIntegerMap.entrySet();
        Iterator<Map.Entry<EngineEntity, Integer>> engineIterator = engineEntry.iterator();
        Iterator<Map.Entry<SuspensionEntity, Integer>> suspIterator = suspEntry.iterator();
        Iterator<Map.Entry<BodyEntity, Integer>> bodyIterator = bodyEntry.iterator();
        Iterator<Map.Entry<SalonEntity, Integer>> salonIterator = salonEntry.iterator();
        Iterator<Map.Entry<SeparaterlyEntity, Integer>> sepIterator = sepEntry.iterator();
        Iterator<Map.Entry<AdditionEntity, Integer>> additionIterator = additionEntry.iterator();
        List<Object> engineList = new ArrayList<>();
        List<Object> suspList = new ArrayList<>();
        List<Object> bodyList = new ArrayList<>();
        List<Object> salonList = new ArrayList<>();
        List<Object> sepList = new ArrayList<>();
        List<Object> additionList = new ArrayList<>();
        while (engineIterator.hasNext()){
            Map.Entry<EngineEntity, Integer> next = engineIterator.next();
            engineList.add(next.getKey());
            engineList.add(next.getValue());
        }
        while (suspIterator.hasNext()){
            Map.Entry<SuspensionEntity, Integer> next = suspIterator.next();
            suspList.add(next.getKey());
            suspList.add(next.getValue());
        }
        while (bodyIterator.hasNext()){
            Map.Entry<BodyEntity, Integer> next = bodyIterator.next();
            bodyList.add(next.getKey());
            bodyList.add(next.getValue());
        }
        while (salonIterator.hasNext()){
            Map.Entry<SalonEntity, Integer> next = salonIterator.next();
            salonList.add(next.getKey());
            salonList.add(next.getValue());
        }
        while (sepIterator.hasNext()){
            Map.Entry<SeparaterlyEntity, Integer> next = sepIterator.next();
            sepList.add(next.getKey());
            sepList.add(next.getValue());
        }
        while (additionIterator.hasNext()){
            Map.Entry<AdditionEntity, Integer> next = additionIterator.next();
            additionList.add(next.getKey());
            additionList.add(next.getValue());
        }
        int max2 = Math.max(engineList.size(),suspList.size());
        int max4 = Math.max(bodyList.size(),salonList.size());
        int max6 = Math.max(sepList.size(),additionList.size());
        int max8 = Math.max(max2,max4);
        int maxRes = Math.max(max6,max8);
        for (int i = 0; i < maxRes-1; i++) {
            EngineEntity engine = null;
            int engineCount = 0;
            SuspensionEntity suspensionEntity = null;
            int suspCount = 0;
            BodyEntity bodyEntity = null;
            int bodyCount = 0;
            SalonEntity salonEntity = null;
            int salonCount = 0;
            SeparaterlyEntity separaterlyEntity = null;
            int sepCount = 0;
            AdditionEntity additionEntity = null;
            int additionCount = 0;
            try{
                engine = (EngineEntity) engineList.get(i);
                engineCount = (int) engineList.get(i+1);
            }catch (Throwable e){

            }
            try{
                suspensionEntity = (SuspensionEntity) suspList.get(i);
                suspCount = (int) suspList.get(i+1);
            }catch (Throwable e){

            }
            try{
                bodyEntity = (BodyEntity) bodyList.get(i);
                bodyCount = (int) bodyList.get(i+1);
            }catch (Throwable e){

            }
            try{
                salonEntity = (SalonEntity) salonList.get(i);
                salonCount = (int) salonList.get(i+1);
            }catch (Throwable er){

            }
            try{
                separaterlyEntity = (SeparaterlyEntity) sepList.get(i);
                sepCount = (int) sepList.get(i+1);
            }catch (Throwable e){

            }
            try{
                additionEntity = (AdditionEntity) additionList.get(i);
                additionCount = (int) additionList.get(i+1);
            }catch (Throwable e){

            }
            SavedEntity savedEntity = SavedEntity.builder().
                    name(name).
                    engineEntity(engine).
                    engineCount(engineCount).
                    suspensionEntity(suspensionEntity).
                    suspensionCount(suspCount).
                    bodyEntity(bodyEntity).
                    bodyCount(bodyCount).
                    salonEntity(salonEntity).
                    salonCount(salonCount).
                    separaterlyEntity(separaterlyEntity).
                    separaterlyCount(sepCount).
                    additionEntity(additionEntity).
                    additionCount(additionCount).
                    build();
            savedEntities.add(savedEntity);

        }
        return savedEntities;
    }
}
