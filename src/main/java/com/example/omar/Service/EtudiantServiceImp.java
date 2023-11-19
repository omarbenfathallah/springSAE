package com.example.omar.Service;

import com.example.omar.Entity.Etudiant;
import com.example.omar.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImp  implements  EtudiantService{
    private final EtudiantRepository  etudiantRepository;

    @Autowired
    public EtudiantServiceImp(EtudiantRepository  etudiantRepository){
        this.etudiantRepository=etudiantRepository;
    }
    @Override
    public Etudiant createEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
    @Override
    public List<Etudiant> getAllEtudiant(){
        return etudiantRepository.findAll();
    }
    @Override
    public Optional<Etudiant> getEtudiantById(long id){
        return  etudiantRepository.findById(id);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant, long id) {
            return etudiantRepository.save(etudiant);
    }
    @Override
    public void deleteEtudiant(long id) {
            etudiantRepository.deleteById(id);
    }
}
