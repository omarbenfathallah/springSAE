package com.example.omar.Service;

import com.example.omar.Entity.Foyer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FoyerService {
    Foyer createFoyer(Foyer foyer);
    List<Foyer> getAllFoyer();
    Optional<Foyer> getFoyerById(long id);
    Foyer updateFoyer(Foyer foyer,long id);
    void deleteFoyer(long id);

    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite) ;
}
