package com.example.omar.Service;

import com.example.omar.Entity.Bloc;
import com.example.omar.Entity.Foyer;
import com.example.omar.Entity.Universite;
import com.example.omar.Repository.BlocRepository;
import com.example.omar.Repository.FoyerRepository;
import com.example.omar.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FoyerServiceImp implements FoyerService{

    private final FoyerRepository foyerRepository;
    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    BlocRepository blocRepository;

    @Autowired
    public FoyerServiceImp(FoyerRepository foyerRepository){
        this.foyerRepository=foyerRepository;
    }

    @Override
    public Foyer createFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public List<Foyer> getAllFoyer() {
        return foyerRepository.findAll();
    }

    @Override
    public Optional<Foyer> getFoyerById(long id) {
        return foyerRepository.findById(id);
    }

    @Override
    public Foyer updateFoyer(Foyer foyer, long id) {
        return foyerRepository.save(foyer);
    }

    @Override
    public void deleteFoyer(long id) {
        foyerRepository.findById(id);
    }

//    @Override
//    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
//        Universite universite = universiteRepository.findById(idUniversite).get();
//
//
//        foyer.setUniversite(universite);
//
//        for (Bloc bloc : foyer.getBloc()) {
//            bloc.setFoyer(foyer);
//
//        }
//
//        return foyerRepository.save(foyer);
//    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).get();

            foyer.setUniversite(universite);

            Set<Bloc> blocs = new HashSet<>();

            for (Bloc bloc : foyer.getBloc()) {

                Bloc existingBloc = blocRepository.findById(bloc.getBlocId()).orElse(null);

                if (existingBloc != null) {
                    existingBloc.setFoyer(foyer);
                    blocs.add(existingBloc);
                }

            }

            foyer.setBloc(blocs);

            return foyerRepository.save(foyer);

    }



}
