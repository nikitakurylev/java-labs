package kafka;

import itmo.tech.main.entity.Cat;
import itmo.tech.main.entity.CatDTO;
import itmo.tech.main.entity.CatMapper;
import itmo.tech.main.entity.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import service.CatService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ComponentScan("itmo.tech.main.entity")
public class CatListener {

    CatService catService;
    CatMapper catMapper;

    @Autowired
    public CatListener(CatService catService, CatMapper catMapper) {
        this.catService = catService;
        this.catMapper = catMapper;
    }

    @KafkaListener(topics = "${kafka.request.topic}", groupId = "${kafka.group.id}")
    @SendTo
    public CatDTO handle(RequestInfo requestInfo) {
        CatDTO entity = null;
        Method method = null;
        try {
            method = (CatService.class).getMethod(requestInfo.getMethodName(), getTypes(requestInfo.getArgs()));
            entity = catMapper.toDTO((Cat) method.invoke(catService, requestInfo.getArgs()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        try {
//            Method method = (CatService.class).getMethod(requestInfo.getMethodName(), getTypes(requestInfo.getArgs()));
//            try {
//                entity = method.invoke(catService, requestInfo.getArgs());
//                try {
//                    catMapper.getDTO(entity);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                }
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
        return entity;
    }

    private Class<?>[] getTypes(Object... args) {
        List<Class<?> > types = new ArrayList<>();
        Arrays.stream(args).forEach(e -> types.add(e.getClass()));
        return types.toArray(new Class<?>[0]);
    }
}