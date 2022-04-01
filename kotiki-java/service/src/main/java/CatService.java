import entity.CatEntity;
import entity.OwnerEntity;

public interface CatService {
    void AddCat(CatEntity cat, OwnerEntity owner);
    void RemoveCat(CatEntity cat);
    void AddOwner(OwnerEntity owner);
    void RemoveOwner(OwnerEntity owner);
    void MakeFriends(CatEntity cat1, CatEntity cat2);
    void Adopt(OwnerEntity newOwner, CatEntity cat);
}
