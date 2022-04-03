import entity.CatEntity;
import entity.OwnerEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CatEntity barsik = new CatEntity();
        barsik.setName("Barsik");

        CatEntity murzik = new CatEntity();
        murzik.setName("Murzik");

        OwnerEntity ivan = new OwnerEntity();
        ivan.setName("Ivan");

        OwnerEntity petr = new OwnerEntity();
        petr.setName("Petr");

        SimpleCatService simpleCatService = new SimpleCatService(new DBCatRepository("default"));
        simpleCatService.AddOwner(ivan);
        simpleCatService.AddOwner(petr);
        simpleCatService.AddCat(barsik, ivan);
        simpleCatService.AddCat(murzik, petr);
        simpleCatService.MakeFriends(murzik, barsik);
    }
}
