import entity.CatEntity;
import entity.OwnerEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CatServiceTest {
    CatEntity  barsik, murzik;
    OwnerEntity ivan, petr;
    repository.CatRepository catRepository;
    repository.OwnerRepository ownerRepository;
    service.CatServiceImpl catService;

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

        catRepository = mock(repository.CatRepository.class);
        ownerRepository = mock(repository.OwnerRepository.class);
        when(catRepository.findById(barsik.getCatId())).thenReturn(Optional.of(barsik) );
        when(catRepository.findById(murzik.getCatId())).thenReturn(Optional.of(murzik));
        when(ownerRepository.findById(ivan.getOwnerId())).thenReturn(Optional.of(ivan));
        when(ownerRepository.findById(petr.getOwnerId())).thenReturn(Optional.of(petr));

        catService = new service.CatServiceImpl(catRepository, ownerRepository);
    }

    @Test
    public void CatAddTest(){
        catService.AddCat(barsik, ivan);
        assertEquals(barsik, ivan.getCats().get(0));
        assertEquals(barsik.getOwnerId().intValue(), ivan.getOwnerId());
    }

    @Test
    public void CatRemoveTest(){
        catService.RemoveCat(murzik);
        assertEquals(0, petr.getCats().size());
    }

    @Test
    public void CatFriendsTest(){
        catService.MakeFriends(barsik, murzik);
        assertEquals(murzik.getFriends().get(0), barsik);
        assertEquals(barsik.getFriends().get(0), murzik);
    }
}
