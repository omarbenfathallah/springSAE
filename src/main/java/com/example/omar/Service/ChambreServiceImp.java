package com.example.omar.Service;

import com.example.omar.Entity.Chambre;
import com.example.omar.Entity.TypeChambre;
import com.example.omar.Repository.ChambreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServiceImp implements ChambreService {

    private static final Logger logger = LoggerFactory.getLogger(ChambreServiceImp.class);


    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public Chambre createChambre(Chambre chambre) {
        logger.info("Creating chambre: {}", chambre);
        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> getAllChambre() {
        logger.info("Getting all chambres");
        return chambreRepository.findAll();
    }

    @Override
    public Optional<Chambre> getChambreById(long id) {
        logger.info("Getting chambre by ID: {}", id);
        return chambreRepository.findById(id);
    }

    @Override
    public Chambre updateChambre(Chambre chambre, long id) {
        logger.info("Updating chambre with ID {}: {}", id, chambre);
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
        logger.info("Deleting chambre with ID: {}", id);
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        logger.info("Getting chambres by university name: {}", nomUniversite);
        return chambreRepository.findByBloc_Foyer_Universite_NomUniversite(nomUniversite);
    }

    // V1 By KeyWords
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        logger.info("Getting chambres by block ID {} and type {}", idBloc, typeC);
        return chambreRepository.findChambreByBloc_BlocIdAndTypeC(idBloc, typeC);
    }

    // V2 By JPQL
    @Override
    public List<Chambre> getChambreParBlocEtType(long idBloc, TypeChambre typeC) {
        logger.info("Getting chambres by block ID {} and type {} using JPQL", idBloc, typeC);
        return chambreRepository.findChambreByBlocIdAndTypeC(idBloc, typeC);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Exécutez la tâche tous les premiers jours de chaque mois
    @Override
    public List<Chambre> getChambresNonReserveesCetteAnnee(Date date) {
        logger.info("Getting non-reserved rooms for the year");
        return chambreRepository.findChambresNonReserveesPourAnnee(date);
    }

    @Override
    public List<Chambre> getChambreByAddressUniversite(String a) {
        return chambreRepository.findByBloc_Foyer_Universite_Adresse(a);
    }
}
