package service;

import itmo.tech.main.entity.Cat;
import enumeration.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CatRepository;

import java.util.List;

@Service
@ComponentScan("repository")
public class CatService {

    private CatRepository ctRepository;
    @Autowired
    public CatService(CatRepository ctRepository) {
        this.ctRepository = ctRepository;
    }

    public CatRepository getCatRepository() {
        return ctRepository;
    }

    public void setCtRepository(CatRepository ctRepository) {
        this.ctRepository = ctRepository;
    }

    public void saveCat(Cat cat) {
        ctRepository.save(cat);
    }

    public Cat FindById(Integer id){
        return ctRepository.findById(id).orElse(new Cat());
    }

    public List<Cat> FindByColor(String color) {
        if(color.equals("red")) {
            return ctRepository.findAllByColor(Color.red);
        }
        if(color.equals("blue")) {
            return ctRepository.findAllByColor(Color.blue);
        }
        else{
            return ctRepository.findAllByColor(Color.green);
        }
    }
    public Cat FindByName(String name){
        return ctRepository.findByName(name);
    }

    public List<Cat> FindAll() {
        return ctRepository.findAll();
    }
}
