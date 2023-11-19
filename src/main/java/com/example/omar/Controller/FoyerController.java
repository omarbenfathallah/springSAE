package com.example.omar.Controller;

import com.example.omar.Entity.Foyer;
import com.example.omar.Service.FoyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foyer")
public class FoyerController {
    private final FoyerService  foyerService;

    @Autowired
    public FoyerController(FoyerService foyerService){
        this.foyerService=foyerService;
    }

    @GetMapping("/all")
    public List<Foyer> getAllFoyer(){
       return foyerService.getAllFoyer();
    }

    @GetMapping("/{id}")
    public Optional<Foyer> getFoyerById(@PathVariable long id){
        return foyerService.getFoyerById(id);
    }

    @PostMapping("/create")
    public Foyer createFoyer(@RequestBody Foyer foyer){
        return foyerService.createFoyer(foyer);
    }

    @PutMapping("/update/{id}")
    public Foyer updateFoyer(@RequestBody Foyer foyer ,@PathVariable long id){
        return foyerService.updateFoyer(foyer,id);
    }

    @DeleteMapping("/delete")
    void deleteFoyer(@PathVariable long id){
        foyerService.deleteFoyer(id);
    }

    @PostMapping("/ajouter-et-affecter")
    public ResponseEntity<Foyer> ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @RequestParam long idUniversite) {
        Foyer nouveauFoyer = foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
        return new ResponseEntity<>(nouveauFoyer, HttpStatus.CREATED);
    }


}
