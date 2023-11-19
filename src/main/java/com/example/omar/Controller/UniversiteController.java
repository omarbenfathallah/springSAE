package com.example.omar.Controller;

import com.example.omar.Entity.Etudiant;
import com.example.omar.Entity.Foyer;
import com.example.omar.Entity.Universite;
import com.example.omar.Service.UniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universite")
public class UniversiteController {

    @Autowired
    private UniversiteService universiteService;

    @GetMapping("/all")
    public List<Universite> getALLUniversite(){
      return  universiteService.getAllUniversite();
    }

    @GetMapping("/{id}")
    public Universite getUniversiteById(@PathVariable long id){
        return universiteService.getUniversiteById(id);
    }

    @PostMapping("/add")
    public Universite adduniversite(@RequestBody Universite universite){
        return universiteService.addUniversite(universite) ;
    }


    @PutMapping("/update/{id}")
    public  Universite updateUniversite(@RequestBody Universite universite,@PathVariable long id){
        return universiteService.updateUniversite(universite,id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteUniversite(@PathVariable long id){
        universiteService.deleteUniversite(id);
    }


    @PostMapping("/affecter/{idFoyer}/{nom}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer, @PathVariable String nom) {
        Universite universite = universiteService.affecterFoyerAUniversite(idFoyer, nom);
        return (universite) ;
    }

     @PutMapping("/desaffecter/{idFoyer}/{idUniversite}")
    public  Universite desaffecterFoyer (@PathVariable("idFoyer") Long idFoyer ,@PathVariable("idUniversite") Long idUniversite){
        return universiteService.desaffecterFoyerAUniversite(idFoyer,idUniversite);
     }

    //v2
    @PostMapping("/desaffecterFoyer")
    public Universite desaffecterFoyerAUniversite(@RequestParam long idUniversite) {
        Universite universite = universiteService.desaffecterFoyerAUniversite(idUniversite);
       return  universite;
    }

}
