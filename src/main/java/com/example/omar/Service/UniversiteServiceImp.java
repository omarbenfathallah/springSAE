package com.example.omar.Service;

import com.example.omar.Entity.Foyer;
import com.example.omar.Entity.Universite;
import com.example.omar.Repository.FoyerRepository;
import com.example.omar.Repository.UniversiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UniversiteServiceImp implements UniversiteService{
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    FoyerRepository foyerRepository;


    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> getAllUniversite() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(long id) {
        return universiteRepository.findById(id).get();
    }

    @Override
    public Universite updateUniversite(Universite universite, long id) {
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(long id) {
        universiteRepository.deleteById(id);
    }


    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {

        Foyer foyer = foyerRepository.findById(idFoyer).get();
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        foyer.setUniversite(universite);
        foyerRepository.save(foyer);
        return universite;
    }
//version2
    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {

        Universite universite = universiteRepository.findById(idUniversite).get();

            Foyer foyer = universite.getFoyer();

                foyer.setUniversite(null);
                foyerRepository.save(foyer);

        return universite;
    }



    //version1
    @Override
    public Universite desaffecterFoyerAUniversite (long idFoyer,long idUniversite)  {

      Foyer foyer = foyerRepository.findById(idFoyer).get();
      Universite universite= universiteRepository.findById(idUniversite).get();


          if(foyer.getUniversite().getIdUniversite()==idUniversite){
              foyer.setUniversite(null);
              foyerRepository.save(foyer);

          }

      return universite;

    }



}
