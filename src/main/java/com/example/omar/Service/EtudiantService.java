package com.example.omar.Service;

import com.example.omar.Entity.Etudiant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EtudiantService {
    Etudiant createEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiant();
    Optional<Etudiant> getEtudiantById(long id);
    Etudiant updateEtudiant(Etudiant etudiant , long id);
    void deleteEtudiant(long id);
}
