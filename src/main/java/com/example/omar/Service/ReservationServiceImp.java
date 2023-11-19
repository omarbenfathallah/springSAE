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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);

        if (chambre == null || etudiant == null) {
            throw new IllegalArgumentException("Chambre ou étudiant non trouvé");
        }

        if (!capaciteMax(chambre)) {
            throw new IllegalArgumentException("La capacité maximale de la chambre a été atteinte");
        }

        Long numChambre = chambre.getNumeroChambre();
        Bloc bloc = chambre.getBloc();
        String nomBloc = bloc.getNomBloc();
        Date today = new Date();

        Reservation reservation = new Reservation();
        String numReservation = numChambre + "-" + nomBloc + "-" + today.getTime();
        reservation.setIdReservation(numReservation);
        reservation.setEstValide(true);
        reservation.setAnneeReservation(today);
        reservation.getEtudiant().add(etudiant);

        chambre.getReservations().add(reservation);
        etudiant.getReservation().add(reservation);

        reservationRepository.save(reservation);
        chambreRepository.save(chambre);
        etudiantRepository.save(etudiant);

        return reservation;
    }

    private boolean capaciteMax(Chambre chambre) {
        int nombreReservationsParChambre = chambre.getReservations().size();
        int capaciteMax = getCapaciteMaximaleSelonType(chambre.getTypeC());

        return nombreReservationsParChambre < capaciteMax;
    }

    private int getCapaciteMaximaleSelonType(TypeChambre typeChambre) {
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
    boolean capaciteMaxs(Chambre chambre){
        int nombreResParChambre=chambre.getReservations().size();
        System.out.println("le nombre par chambre est "+nombreResParChambre);
        int capaciteMax= getCapaciteMaximaleSelonType(chambre.getTypeC());
        System.out.println("la capcité par chambre est "+getCapaciteMaximaleSelonType(chambre.getTypeC()));
      //  int capaciteMax= getCapaciteMaximaleSelonType(chambre.getTypeC());
        return nombreResParChambre<capaciteMax;
    }

    }
//        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
//
//        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);
//
//        int capaciteMaximale = 0;
//        TypeChambre typeChambre = chambre.getTypeC();
//
//        switch (typeChambre) {
//            case SIMPLE:
//                capaciteMaximale = 1;
//                break;
//            case DOUBLE:
//                capaciteMaximale = 2;
//                break;
//            case TRIPLE:
//                capaciteMaximale = 3;
//                break;
//        }
//
//        int nombreReservations = (int) chambre.getReservations().stream()
//                .filter(reservation -> reservation.isEstValide())
//                .count();
//
//        if (nombreReservations >= capaciteMaximale) {
//            throw new RuntimeException("La capacité maximale de la chambre est atteinte");
//        }
//
//        Reservation reservation = new Reservation();
//        reservation.setIdReservation(genererNumeroReservation(chambre));
//        reservation.setAnneeReservation(new Date());
//        reservation.setEstValide(true);
//
//        chambre.getReservations().add(reservation);
//        etudiant.getReservation().add(reservation);
//
////        etudiantRepository.save(etudiant);
//
//        chambreRepository.save(chambre);
//
//        return reservation;
//    }
//
//    String genererNumeroReservation(Chambre chambre) {
//        String numChambre = String.valueOf(chambre.getNumeroChambre());
//        String nomBloc = chambre.getBloc().getNomBloc();
//        String anneeUniversitaire = getAnneeUniversitaireSysdate();
//
//        return String.format("%s-%s-%s", numChambre, nomBloc, anneeUniversitaire);
//    }
//
//    private String getAnneeUniversitaireSysdate() {
//        // Utiliser la classe LocalDateTime pour obtenir la date et l'heure actuelles
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        // Formater la date selon le besoin ("yyyy-MM-dd-HH-mm-ss")
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
//        String formattedDateTime = currentDateTime.format(formatter);
//
//        return formattedDateTime;
//    }
