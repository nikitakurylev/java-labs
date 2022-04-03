import entity.CatEntity;
import entity.OwnerEntity;
import org.stringtemplate.v4.ST;

import javax.persistence.*;
import java.util.List;

public class DBCatRepository implements CatRepository{
    private final EntityManager entityManager;

    public DBCatRepository(String persistenceUnitName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public CatEntity GetCat(int catId) {
        CatEntity cat = entityManager.find(CatEntity.class, catId);
        if (cat == null) {
            throw new EntityNotFoundException("Can't find cat with ID "
                    + catId);
        }
        return cat;
    }

    @Override
    public void AddCat(CatEntity cat) {
        AddEntity(cat);
    }

    @Override
    public void UpdateCat(CatEntity cat) {
        UpdateEntity(cat);
    }

    @Override
    public void RemoveCat(CatEntity cat) {
        RemoveEntity(cat);
    }

    @Override
    public OwnerEntity GetOwner(int ownerId) {
        OwnerEntity owner = entityManager.find(OwnerEntity.class, ownerId);
        if (owner == null) {
            throw new EntityNotFoundException("Can't find owner with ID "
                    + ownerId);
        }
        return owner;
    }

    @Override
    public void AddOwner(OwnerEntity owner) {
        AddEntity(owner);
    }

    @Override
    public void UpdateOwner(OwnerEntity owner) {
        UpdateEntity(owner);
    }

    @Override
    public void RemoveOwner(OwnerEntity owner) {
        RemoveEntity(owner);
    }

    private void AddEntity(Object object){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(object);
        transaction.commit();
    }

    private void UpdateEntity(Object object){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(object);
        transaction.commit();
    }

    private void RemoveEntity(Object object){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(object);
        transaction.commit();
    }
}
