package com.example.omar.Controller;

import com.example.omar.Entity.Etudiant;
import com.example.omar.Service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController  {
    private  final EtudiantService etudiantService;

    @Autowired
    public EtudiantController (EtudiantService etudiantService){
        this.etudiantService = etudiantService;
    }

    @GetMapping("/all")
    public List<Etudiant> getAllEtudiant(){
        return etudiantService.getAllEtudiant();
    }

    @GetMapping("/{id}")
    public Optional<Etudiant> getEtudiantById(@PathVariable("id") long id){
        return  etudiantService.getEtudiantById(id);
    }
    @PostMapping("/create")
    public Etudiant createEtudiant (@RequestBody Etudiant etudiant){
        return etudiantService.createEtudiant(etudiant);
    }

    @PutMapping("/update/{id}")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant ,@PathVariable("id")  long id){
        return etudiantService.updateEtudiant(etudiant,id);
    }
    @DeleteMapping("/delete/{id}")
    void DeleteEtudaint(@PathVariable("id") long id){
        etudiantService.deleteEtudiant(id);
    }


}
