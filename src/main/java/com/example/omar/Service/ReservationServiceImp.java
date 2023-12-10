package com.example.omar.Service;

import com.example.omar.Entity.*;
import com.example.omar.Repository.ChambreRepository;
import com.example.omar.Repository.EtudiantRepository;
import com.example.omar.Repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    public ReservationServiceImp(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation, long id) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void ajouterReservation(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);
        Long numChambre=chambre.getNumeroChambre();
        Bloc bloc = chambre.getBloc();
        String nomBloc = bloc.getNomBloc();
        Date today = new Date();
        if (!capaciteMax(chambre)) {
            Reservation reservation = new Reservation();
            String numReservation = numChambre + "-" + nomBloc + "-" + today;
            reservation.setIdReservation(numReservation);
            reservation.setEstValide(true);
            reservation.setAnneeReservation(today);
            String capaciteBlocString = bloc.getCapaciteBloc();
            int capaciteBloc = Integer.parseInt(capaciteBlocString);
            capaciteBloc--;
            bloc.setCapaciteBloc(String.valueOf(capaciteBloc));
            reservationRepository.save(reservation);
            etudiant.getReservation().add(reservation);
            etudiantRepository.save(etudiant);
            chambre.getReservations().add(reservation);
            chambreRepository.save(chambre);
        }
        else {
            System.out.println("vous avez depassez la capacité");
        }
    }




    int getCapaciteMaximaleSelonType(TypeChambre typeChambre) {
        switch (typeChambre) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                throw new IllegalArgumentException("Type de chambre non pris en charge : " + typeChambre);
        }
    }
    boolean capaciteMax(Chambre chambre){
        int nombreResParChambre=chambre.getReservations().size();
        System.out.println("le nombre par chambre est "+nombreResParChambre);
        System.out.println("la capcité par chambre est "+getCapaciteMaximaleSelonType(chambre.getTypeC()));
        int capaciteMax= getCapaciteMaximaleSelonType(chambre.getTypeC());
        return nombreResParChambre==capaciteMax;
    }


    @Override
    public Reservation annulerReservation(long cinEtudiant) {
            Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);
            Reservation reservation = reservationRepository.findDistinctFirstByEtudiant(etudiant);
            Chambre chambre = chambreRepository.findChambreByReservations(reservation);
            reservation.setEstValide(false);
            Bloc bloc = chambre.getBloc();
            String capaciteBlocString = bloc.getCapaciteBloc();
            int capaciteBloc = Integer.parseInt(capaciteBlocString);
            capaciteBloc++;
            bloc.setCapaciteBloc(String.valueOf(capaciteBloc));
            etudiant.setReservation(null);
            chambre.setReservations((null));
            chambreRepository.save(chambre);
            reservationRepository.save(reservation);
            etudiantRepository.save(etudiant);
            return reservation;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        return null;
    }

}
