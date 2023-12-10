package com.example.omar.Service;

import com.example.omar.Entity.Chambre;
import com.example.omar.Entity.Reservation;
import com.example.omar.Entity.TypeChambre;
import com.example.omar.Entity.Universite;
import com.example.omar.Repository.ChambreRepository;
import com.example.omar.Repository.ReservationRepository;
import com.example.omar.Repository.UniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChambreServiceImp implements  ChambreService{
    @Autowired
    private UniversiteRepository universiteRepository;

    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    ReservationRepository reservationRepository;
    public Chambre createChambre(Chambre chambre){
        return chambreRepository.save(chambre) ;
    }

    @Override
    public List<Chambre> getAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public Optional<Chambre> getChambreById(long id) {
        return chambreRepository.findById(id);
    }

    @Override
    public Chambre updateChambre(Chambre chambre, long id) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long id) {
      chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {

        return chambreRepository.findByBloc_Foyer_Universite_NomUniversite(nomUniversite);
    }

    // V1 By KeyWords
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return  chambreRepository.findChambreByBloc_BlocIdAndTypeC(idBloc,typeC);
    }

    //V2 By JPQL
    @Override
    public List<Chambre> getChambreParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findChambreByBlocIdAndTypeC(idBloc,typeC);
    }

    @Scheduled(cron = "0 0 0 1 * ?") // Exécutez la tâche tous les premiers jours de chaque mois
    @Override
    public List<Chambre> getChambresNonReserveesCetteAnnee(Date date) {
        return chambreRepository.findChambresNonReserveesPourAnnee(date);
        }

}
