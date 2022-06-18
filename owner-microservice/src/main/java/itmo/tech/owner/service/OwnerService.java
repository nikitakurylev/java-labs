package itmo.tech.owner.service;


import itmo.tech.owner.entity.Owner;
import itmo.tech.owner.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerRepository getOwnerRepository() {
        return ownerRepository;
    }

    public Owner FindById(int id) {
        return ownerRepository.findById(id).orElse(new Owner());
    }

    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner findByName(String name) {
        return ownerRepository.findByName(name);
    }

}
