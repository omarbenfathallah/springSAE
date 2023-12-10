package com.example.omar.Service;

import com.example.omar.Entity.Etudiant;
import com.example.omar.Repository.EtudiantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImp implements EtudiantService {

    private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceImp.class);

    private final EtudiantRepository etudiantRepository;

    @Autowired
    public EtudiantServiceImp(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Etudiant createEtudiant(Etudiant etudiant) {
        logger.info("Creating etudiant: {}", etudiant);
        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> getAllEtudiant() {
        logger.info("Getting all etudiants");
        return etudiantRepository.findAll();
    }

    @Override
    public Optional<Etudiant> getEtudiantById(long id) {
        logger.info("Getting etudiant by ID: {}", id);
        return etudiantRepository.findById(id);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant, long id) {
        logger.info("Updating etudiant with ID {}: {}", id, etudiant);
        return etudiantRepository.save(etudiant);
    }

    @Override
    public void deleteEtudiant(long id) {
        logger.info("Deleting etudiant with ID: {}", id);
        etudiantRepository.deleteById(id);
    }
}
