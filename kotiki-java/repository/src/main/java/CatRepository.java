
import entity.CatEntity;
import entity.OwnerEntity;

import java.util.List;

public interface CatRepository {
    CatEntity GetCat(int catId);
    void AddCat(CatEntity cat);
    void UpdateCat(CatEntity cat);
    void RemoveCat(CatEntity cat);
    OwnerEntity GetOwner(int ownerId);
    void AddOwner(OwnerEntity owner);
    void UpdateOwner(OwnerEntity owner);
    void RemoveOwner(OwnerEntity owner);
}
