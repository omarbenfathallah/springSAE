package com.example.omar.Service;

import com.example.omar.Entity.Bloc;
import com.example.omar.Entity.Chambre;
import com.example.omar.Repository.BlocRepository;
import com.example.omar.Repository.ChambreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlocServiceImp implements BlocService {

    private static final Logger logger = LoggerFactory.getLogger(BlocServiceImp.class);

    private final BlocRepository blocRepository;
    private final ChambreRepository chambreRepository;

    @Autowired
    public BlocServiceImp(BlocRepository blocRepository, ChambreRepository chambreRepository) {
        this.blocRepository = blocRepository;
        this.chambreRepository = chambreRepository;
    }

    @Override
    public Bloc createBloc(Bloc bloc) {
        logger.info("Creating bloc: {}", bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBloc() {
        logger.info("Getting all blocs");
        return blocRepository.findAll();
    }

    @Override
    public Optional<Bloc> getBlocById(long id) {
        logger.info("Getting bloc by ID: {}", id);
        return blocRepository.findById(id);
    }

    @Override
    public Bloc updateBloc(Bloc bloc, long id) {
        logger.info("Updating bloc with ID {}: {}", id, bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
        logger.info("Deleting bloc with ID: {}", id);
        blocRepository.deleteById(id);
    }

    // V1
    @Override
    public Bloc afffecterChambresABloc(List<Long> numChambre, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);

        for (Long numCh : numChambre) {
            Chambre chambre = chambreRepository.findByNumeroChambre(numCh);
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }

        logger.info("Assigned rooms {} to bloc {}", numChambre, nomBloc);
        return bloc;
    }

    // V2
    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        if (bloc != null) {
            for (Long numCh : numChambre) {
                Chambre chambre = chambreRepository.findByNumeroChambre(numCh);
                if (chambre != null) {
                    chambre.setBloc(bloc);
                    chambreRepository.save(chambre);
                } else {
                    logger.warn("Chambre with number {} not found.", numCh);
                }
            }

            logger.info("Assigned rooms {} to bloc with ID {}", numChambre, idBloc);
            return bloc;
        } else {
            logger.warn("Bloc with ID {} not found.", idBloc);
            return null; // Vous pouvez ajuster le comportement en conséquence
        }
    }
}
