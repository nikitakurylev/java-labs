package service;

import entity.CatEntity;
import entity.OwnerEntity;

import java.util.List;

public interface CatService {
    void AddCat(CatEntity cat, OwnerEntity owner);
    void RemoveCat(CatEntity cat);
    void MakeFriends(CatEntity cat1, CatEntity cat2);
    CatEntity FindCatById(Integer id);
    List<CatEntity> GetCats();
}
