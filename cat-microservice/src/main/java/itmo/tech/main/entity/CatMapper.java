package itmo.tech.main.entity;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Component
public class CatMapper {
    public CatDTO toDTO (Cat cat) {
        return new CatDTO(cat.getCatId(), cat.getName(), cat.getDateOfBirth(),
                cat.getType(), cat.getColor(), cat.getOwnerId(), cat.getFriends());
    }

    public Cat toDomain(CatDTO cat) {
        return new Cat(cat.getCatId(), cat.getName(), cat.getDateOfBirth(),
                cat.getType(), cat.getColor(), cat.getOwnerId(), cat.getFriends());
    }

    public Object getDTO(Object domain)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> classDto = Class.forName(domain.getClass().getName() + "DTO");
        Object DTO = classDto.getConstructor().newInstance();
        Method[] methods = domain.getClass().getMethods();
        Arrays.stream(methods).forEach(method -> {
            if(method.getName().contains("get")) {
                Method setter = null;
                Class<?> returnType = method.getReturnType();
                try {
                    setter = classDto.getMethod(method.getName().replaceAll("get", "set"), returnType);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    Object returnObject = returnType.getConstructor().newInstance();
                    returnObject = method.invoke(domain);
                    setter.invoke(DTO, method.invoke(domain));
                } catch (IllegalAccessException | NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        });
        return DTO;
    }
}
