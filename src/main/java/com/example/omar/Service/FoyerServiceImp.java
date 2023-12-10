package com.example.omar.Service;

import com.example.omar.Entity.Bloc;
import com.example.omar.Entity.Foyer;
import com.example.omar.Entity.Universite;
import com.example.omar.Repository.BlocRepository;
import com.example.omar.Repository.FoyerRepository;
import com.example.omar.Repository.UniversiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FoyerServiceImp implements FoyerService {

    private static final Logger logger = LoggerFactory.getLogger(FoyerServiceImp.class);

    private final FoyerRepository foyerRepository;

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    BlocRepository blocRepository;

    @Autowired
    public FoyerServiceImp(FoyerRepository foyerRepository) {
        this.foyerRepository = foyerRepository;
    }

    @Override
    public Foyer createFoyer(Foyer foyer) {
        logger.info("Creating foyer: {}", foyer);
        return foyerRepository.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyer() {
        logger.info("Getting all foyers");
        return foyerRepository.findAll();
    }

    @Override
    public Optional<Foyer> getFoyerById(long id) {
        logger.info("Getting foyer by ID: {}", id);
        return foyerRepository.findById(id);
    }

    @Override
    public Foyer updateFoyer(Foyer foyer, long id) {
        logger.info("Updating foyer with ID {}: {}", id, foyer);
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(long id) {
        logger.info("Deleting foyer with ID: {}", id);
        foyerRepository.deleteById(id);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        if (universite != null) {
            foyer.setUniversite(universite);

            Set<Bloc> blocs = new HashSet<>();

            for (Bloc bloc : foyer.getBloc()) {
                Bloc existingBloc = blocRepository.findById(bloc.getBlocId()).orElse(null);

                if (existingBloc != null) {
                    existingBloc.setFoyer(foyer);
                    blocs.add(existingBloc);
                } else {
                    logger.warn("Bloc with ID {} not found.", bloc.getBlocId());
                }
            }

            foyer.setBloc(blocs);

            logger.info("Foyer {} added and assigned to universite {}.", foyer, universite);
            return foyerRepository.save(foyer);
        } else {
            logger.warn("Universite with ID {} not found.", idUniversite);
            return null; // Vous pouvez ajuster le comportement en conséquence
        }
    }
}
