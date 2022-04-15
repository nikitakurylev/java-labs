package service;

import entity.CatEntity;
import entity.OwnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import repository.CatRepository;
import repository.OwnerRepository;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("repository")
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public void AddOwner(OwnerEntity owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void RemoveOwner(OwnerEntity owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void Adopt(OwnerEntity newOwner, CatEntity cat) {
        Optional<OwnerEntity> oldOwner = ownerRepository.findById(cat.getOwnerId());
        if(oldOwner.isPresent()){
            oldOwner.get().getCats().remove(cat);
            newOwner.getCats().add(cat);
            cat.setOwnerId(newOwner.getOwnerId());
            ownerRepository.save(newOwner);
            ownerRepository.save(oldOwner.get());
            catRepository.save(cat);
        }
    }

    @Override
    public OwnerEntity FindOwnerById(Integer id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public List<OwnerEntity> GetOwners() {
        return ownerRepository.findAll();
    }
}
