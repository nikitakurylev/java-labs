import entity.CatEntity;
import entity.OwnerEntity;

import java.util.ArrayList;

public class SimpleCatService implements CatService {

    private final CatRepository catRepository;

    public SimpleCatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    @Override
    public void AddCat(CatEntity cat, OwnerEntity owner) {
        cat.setOwnerId(owner.getOwnerId());
        if(owner.getCats() == null)
            owner.setCats(new ArrayList<>());
        owner.getCats().add(cat);
        cat.setOwnerId(owner.getOwnerId());
        catRepository.AddCat(cat);
        catRepository.UpdateOwner(owner);
    }

    @Override
    public void RemoveCat(CatEntity cat) {
        OwnerEntity owner = catRepository.GetOwner(cat.getOwnerId());
        owner.getCats().remove(cat);
        catRepository.RemoveCat(cat);
        catRepository.UpdateOwner(owner);
    }

    @Override
    public void AddOwner(OwnerEntity owner) {
        catRepository.AddOwner(owner);
    }

    @Override
    public void RemoveOwner(OwnerEntity owner) {
        catRepository.RemoveOwner(owner);
    }

    @Override
    public void MakeFriends(CatEntity cat1, CatEntity cat2) {
        if(!cat1.getFriends().contains(cat2))
            cat1.getFriends().add(cat2);
        if(!cat2.getFriends().contains(cat1))
            cat2.getFriends().add(cat1);
        catRepository.UpdateCat(cat1);
        catRepository.UpdateCat(cat2);
    }

    @Override
    public void Adopt(OwnerEntity newOwner, CatEntity cat) {
        OwnerEntity oldOwner = catRepository.GetOwner(cat.getOwnerId());
        oldOwner.getCats().remove(cat);
        newOwner.getCats().add(cat);
        cat.setOwnerId(newOwner.getOwnerId());
        catRepository.UpdateOwner(newOwner);
        catRepository.UpdateOwner(oldOwner);
        catRepository.UpdateCat(cat);
    }
}
