package controllers;

import entity.OwnerEntity;
import entity.OwnerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import repository.UserEntity;
import security.UserService;
import service.OwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ComponentScan("service")
@RequestMapping
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<OwnerInfo> getCat(@PathVariable Integer id) {

        OwnerEntity owner = ownerService.FindOwnerById(id);
        if (owner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new OwnerInfo(owner), HttpStatus.OK);
    }

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerInfo>> getCats(@RequestParam Optional<String> name) {
        List<OwnerInfo> result = new ArrayList<OwnerInfo>();
        for (OwnerEntity owner : ownerService.GetOwners())
            result.add(new OwnerInfo(owner));
        if(name.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getName(), name.get())).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
