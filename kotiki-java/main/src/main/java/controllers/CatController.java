package controllers;

import entity.CatEntity;
import entity.CatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CatService;

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

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
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
    public ResponseEntity<List<CatInfo>> getCats(@RequestParam Optional<String> name, @RequestParam Optional<String> breed, @RequestParam Optional<String> color) {
        List<CatInfo> result = new ArrayList<CatInfo>();
        for (CatEntity cat : catService.GetCats())
            result.add(new CatInfo(cat));
        if(name.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getName(), name.get())).collect(Collectors.toList());
        if(breed.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getColor(), breed.get())).collect(Collectors.toList());
        if(color.isPresent())
            result = result.stream().filter(catInfo -> Objects.equals(catInfo.getBreed(), color.get())).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
