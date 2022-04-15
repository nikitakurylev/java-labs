package service;

import entity.CatEntity;
import entity.OwnerEntity;

import java.util.List;

public interface OwnerService {
    void AddOwner(OwnerEntity owner);
    void RemoveOwner(OwnerEntity owner);
    void Adopt(OwnerEntity newOwner, CatEntity cat);
    OwnerEntity FindOwnerById(Integer id);
    List<OwnerEntity> GetOwners();
}
