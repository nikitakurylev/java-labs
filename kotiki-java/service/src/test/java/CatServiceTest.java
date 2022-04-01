import entity.CatEntity;
import entity.OwnerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatServiceTest {
    CatEntity barsik, murzik;
    OwnerEntity ivan, petr;
    CatRepository catRepository;
    SimpleCatService catService;

    @BeforeEach
    public void Initialize() {
        barsik = new CatEntity();
        barsik.setName("Barsik");
        barsik.setCatId(0);

        murzik = new CatEntity();
        murzik.setName("Murzik");
        murzik.setCatId(1);

        ivan = new OwnerEntity();
        ivan.setName("Ivan");
        ivan.setOwnerId(0);
        ivan.setCats(new ArrayList<>());

        petr = new OwnerEntity();
        petr.setName("Petr");
        petr.setOwnerId(1);
        petr.setCats(new ArrayList<>());
        petr.getCats().add(murzik);
        murzik.setOwnerId(petr.getOwnerId());

        catRepository = mock(CatRepository.class);
        when(catRepository.GetCat(barsik.getCatId())).thenReturn(barsik);
        when(catRepository.GetCat(murzik.getCatId())).thenReturn(murzik);
        when(catRepository.GetOwner(ivan.getOwnerId())).thenReturn(ivan);
        when(catRepository.GetOwner(petr.getOwnerId())).thenReturn(petr);

        catService = new SimpleCatService(catRepository);
    }

    @Test
    public void CatAddTest(){
        catService.AddCat(barsik, ivan);
        assertEquals(barsik, ivan.getCats().get(0));
        assertEquals(barsik.getOwnerId(), ivan.getOwnerId());
    }

    @Test
    public void CatRemoveTest(){
        catService.RemoveCat(murzik);
        assertEquals(0, petr.getCats().size());
    }
}
