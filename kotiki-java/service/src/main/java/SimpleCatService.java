import entity.CatEntity;
import entity.OwnerEntity;

public class SimpleCatService implements CatService {

    private final CatRepository catRepository;

    public SimpleCatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    @Override
    public void AddCat(CatEntity cat, OwnerEntity owner) {
        cat.setOwnerId(owner.getOwnerId());
        owner.getCats().add(cat);
        catRepository.AddCat(cat);
    }

    @Override
    public void RemoveCat(CatEntity cat) {
        catRepository.GetOwner(cat.getOwnerId()).getCats().remove(cat);
        catRepository.RemoveCat(cat);
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
