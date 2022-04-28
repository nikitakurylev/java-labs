package service;

import entity.CatEntity;
import entity.OwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CatRepository;
import repository.OwnerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("repository")
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void AddCat(CatEntity cat, OwnerEntity owner) {
        cat.setOwnerId(owner.getOwnerId());
        if(owner.getCats() == null)
            owner.setCats(new ArrayList<>());
        owner.getCats().add(cat);
        cat.setOwnerId(owner.getOwnerId());
        catRepository.save(cat);
        ownerRepository.save(owner);
    }

    @Override
    public void RemoveCat(CatEntity cat) {
        Optional<OwnerEntity> owner = ownerRepository.findById(cat.getOwnerId());
        if(owner.isPresent()){
            owner.get().getCats().remove(cat);
            catRepository.delete(cat);
            ownerRepository.save(owner.get());
        }
    }

    @Override
    public void MakeFriends(CatEntity cat1, CatEntity cat2) {
        if(!cat1.getFriends().contains(cat2))
            cat1.getFriends().add(cat2);
        if(!cat2.getFriends().contains(cat1))
            cat2.getFriends().add(cat1);
        catRepository.save(cat1);
        catRepository.save(cat2);
    }

    @Override
    public CatEntity FindCatById(Integer id) {
        return catRepository.findById(id).orElse(null);
    }

    @Override
    public List<CatEntity> GetCats() {
        return catRepository.findAll();
    }
}
