package controllers;

import entity.CatEntity;
import entity.CatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.CatService;
import service.OwnerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ComponentScan("service")
@RequestMapping
public class CatController {
    private final CatService catService;
    private final OwnerService ownerService;

    @Autowired
    public CatController(CatService catService, OwnerService ownerService) {
        this.catService = catService;
        this.ownerService = ownerService;
    }

    private void CheckName(List<CatInfo> result, Optional<String> name){
        if (name.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getName(), name.get())).collect(Collectors.toList());
    }

    private void CheckBreed(List<CatInfo> result, Optional<String> breed) {
        if (breed.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getColor(), breed.get())).collect(Collectors.toList());
    }

    private void CheckColor(List<CatInfo> result, Optional<String> color) {
        if (color.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getBreed(), color.get())).collect(Collectors.toList());
    }

    @GetMapping("/cat/{id}")
    public ResponseEntity<CatInfo> getCat(@PathVariable Integer id) {

        CatEntity cat = catService.FindCatById(id);
        if (cat == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CatInfo(cat), HttpStatus.OK);
    }

    @GetMapping("/cats")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<CatInfo>> getCats(@RequestParam Optional<String> name, @RequestParam Optional<String> breed, @RequestParam Optional<String> color) {
        List<CatInfo> result = new ArrayList<CatInfo>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        for (CatEntity cat : catService.GetCats().stream().filter(cat -> Objects.equals(ownerService.FindOwnerById(cat.getOwnerId()).getName(), username)).collect(Collectors.toList()))
            result.add(new CatInfo(cat));
        CheckName(result, name);
        CheckBreed(result, breed);
        CheckColor(result, color);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
