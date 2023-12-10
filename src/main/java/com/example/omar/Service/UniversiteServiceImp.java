package com.example.omar.Service;

import com.example.omar.Entity.Foyer;
import com.example.omar.Entity.Universite;
import com.example.omar.Repository.FoyerRepository;
import com.example.omar.Repository.UniversiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteServiceImp implements UniversiteService {

    private static final Logger logger = LoggerFactory.getLogger(UniversiteServiceImp.class);

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    FoyerRepository foyerRepository;

    @Override
    public Universite addUniversite(Universite universite) {
        logger.info("Adding universite: {}", universite);
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> getAllUniversite() {
        logger.info("Getting all universites");
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(long id) {
        logger.info("Getting universite by ID: {}", id);
        return universiteRepository.findById(id).orElse(null);
    }

    @Override
    public Universite updateUniversite(Universite universite, long id) {
        logger.info("Updating universite with ID {}: {}", id, universite);
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(long id) {
        logger.info("Deleting universite with ID: {}", id);
        universiteRepository.deleteById(id);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        if (foyer != null && universite != null) {
            foyer.setUniversite(universite);
            foyerRepository.save(foyer);
            logger.info("Affecting foyer with ID {} to universite '{}'", idFoyer, nomUniversite);
        } else {
            logger.warn("Unable to affect foyer to universite. Foyer or universite not found.");
        }

        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        if (universite != null) {
            Foyer foyer = universite.getFoyer();

            if (foyer != null) {
                foyer.setUniversite(null);
                foyerRepository.save(foyer);
                logger.info("Desaffecting foyer with ID {} from universite '{}'", foyer.getIdFoyer(), universite.getNomUniversite());
            } else {
                logger.warn("No foyer found for universite '{}'", universite.getNomUniversite());
            }
        } else {
            logger.warn("Universite with ID {} not found.", idUniversite);
        }

        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        if (foyer != null && universite != null && foyer.getUniversite() != null && foyer.getUniversite().getIdUniversite() == idUniversite) {
            foyer.setUniversite(null);
            foyerRepository.save(foyer);
            logger.info("Desaffecting foyer with ID {} from universite '{}'", idFoyer, universite.getNomUniversite());
        } else {
            logger.warn("No valid association found between foyer and universite.");
        }

        return universite;
    }
}
