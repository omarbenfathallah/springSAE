package com.example.omar.Controller;

import com.example.omar.Entity.Bloc;
import com.example.omar.Service.BlocService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bloc")
public class BlocController {
    private final BlocService blocService;

    @Autowired
    public BlocController(BlocService blocService) {
        this.blocService = blocService;
    }

    @GetMapping("/all")
    public List<Bloc> getAllBloc() {
        return blocService.getAllBloc();
    }

    @GetMapping("/{id}")
    public Optional<Bloc> getBlocById(@PathVariable long id) {
        return blocService.getBlocById(id);
    }

    @PostMapping("/create")
    public Bloc CreateBloc(@RequestBody Bloc bloc) {
        return blocService.createBloc(bloc);
    }

    @PutMapping("/update/{id}")
    public Bloc updateBLoc(@RequestBody Bloc bloc, @PathVariable long id) {
        return blocService.updateBloc(bloc, id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteBloc(@PathVariable long id) {
        blocService.deleteBloc(id);
    }

    @PostMapping("/affecter/{nomBloc}/chambres")
    public Bloc affecterChambresABloc(@PathVariable String nomBloc, @RequestBody List<Long> numChambres) {
        return blocService.afffecterChambresABloc(numChambres,nomBloc);

    }
    @PostMapping("/affecter-chambres")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre, @RequestParam long idBloc) {
          return blocService.affecterChambresABloc(numChambre, idBloc);

    }


}