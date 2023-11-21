package com.example.omar.Controller;

import com.example.omar.Entity.Chambre;
import com.example.omar.Entity.TypeChambre;
import com.example.omar.Service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/chambre")
public class ChambreController {
    private final ChambreService chambreService ;

    @Autowired
    public ChambreController (ChambreService chambreService){
        this.chambreService=chambreService;
    }

    @PostMapping("/create")
    public Chambre createChambre(@RequestBody Chambre chambre){
        return chambreService.createChambre(chambre);
    }

    @GetMapping("/all")
    public List<Chambre> getAllChambre(){
        return chambreService.getAllChambre();
    }

    @GetMapping("/{id}")
    public Optional<Chambre> getChambreById(@PathVariable long id){
        return chambreService.getChambreById(id);
    }

    @PutMapping("/update/{id}")
    public Chambre updateChambre(@RequestBody Chambre chambre,@PathVariable long id){
        return chambreService.updateChambre(chambre,id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteChambre(@PathVariable long id){
        chambreService.deleteChambre(id);
    }

    @GetMapping("/ByUniversiteName")
    public List<Chambre> getChambresParNomUniversite(@RequestParam  String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }
//V1
    @GetMapping("/chambres/{blocId}/typeC")
    public  List<Chambre> getChambresByBlocAndTypeC(@RequestParam Long blocId, TypeChambre typeChambre){
        return  chambreService.getChambresParBlocEtType(blocId,typeChambre);
    }
//V2
    @GetMapping("/ch/{blocId}/typeC")
    public  List<Chambre> getChambreByBlocAndTypeC(@RequestParam Long blocId, TypeChambre typeChambre){
        return  chambreService.getChambreParBlocEtType(blocId,typeChambre);
    }

    @GetMapping("/nonreserves")
    public ResponseEntity<List<Chambre>> getChambresNonReserveesPourAnnee() {

        Date anneeActuelle = new Date();

        List<Chambre> chambresNonReservees = chambreService.getChambresNonReserveesCetteAnnee(anneeActuelle);
        return new ResponseEntity<>(chambresNonReservees, HttpStatus.OK);
    }

}
