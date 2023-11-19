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
        if (!capaciteMax(chambre)){
            Reservation Res = new Reservation();
            String numReservation = numChambre + "-" + nomBloc + "-" + today;
            Res.setIdReservation(numReservation);
            Res.setEstValide(true);
            Res.setAnneeReservation(today);
            reservationRepository.save(Res);
            etudiant.getReservation().add(Res);
            etudiantRepository.save(etudiant);
            chambre.getReservations().add(Res);
            chambreRepository.save(chambre);
        }else {
            System.out.println("vous avez depassez la capacité");   }
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
        if (etudiant == null) {
            throw new IllegalArgumentException("Étudiant non trouvé");
        }

        Set<Reservation> reservations = etudiant.getReservation();
        if (reservations == null || reservations.isEmpty()) {
            throw new IllegalArgumentException("Aucune réservation associée à cet étudiant");
        }

        Reservation reservation = reservations.iterator().next();
        reservation.setEstValide(false);
        reservation.setEtudiant(null);


        reservationRepository.save(reservation);
        etudiant.setReservation(null); // Set the reservations to null on the etudiant entity
        etudiantRepository.save(etudiant);


        return reservation;
    }

















    //
    @Override
    public Reservation ajouterReservation1(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);

        if (chambre == null || etudiant == null) {
            throw new IllegalArgumentException("Chambre ou étudiant non trouvé");
        }

        if (capaciteMax(chambre)) {
            Long numChambre = chambre.getNumeroChambre();
            Bloc bloc = chambre.getBloc();
            String nomBloc = bloc.getNomBloc();
            Date today = new Date();
            String numReservation = numChambre + "-" + nomBloc + "-" + today;
            Reservation reservation = new Reservation();

            reservation.setIdReservation(numReservation);
            reservation.setEstValide(true);
            reservation.setAnneeReservation(today);
            reservation.setEtudiant(Set.of(etudiant));

            etudiant.getReservation().add(reservation);
            etudiantRepository.save(etudiant);
            chambre.getReservations().add(reservation);
            chambreRepository.save(chambre);
        } else {
            throw new IllegalArgumentException("La capacité maximale de la chambre a été atteinte");
        }
            return  null ;
    }
}
//
//
//
//    private boolean capaciteMax(Chambre chambre) {
//        int nombreReservationsParChambre = chambre.getReservations().size();
//        int capaciteMax = getCapaciteMaximaleSelonType(chambre.getTypeC());
//        return nombreReservationsParChambre < capaciteMax;
//    }
//
//    private int getCapaciteMaximaleSelonType(TypeChambre typeChambre) {
//        switch (typeChambre) {
//            case SIMPLE:
//                return 1;
//            case DOUBLE:
//                return 2;
//            case TRIPLE:
//                return 3;
//            default:
//                throw new IllegalArgumentException("Type de chambre non pris en charge : " + typeChambre);
//        }
//    }
//    boolean capaciteMaxs(Chambre chambre){
//        int nombreResParChambre=chambre.getReservations().size();
//        System.out.println("le nombre par chambre est "+nombreResParChambre);
//        int capaciteMax= getCapaciteMaximaleSelonType(chambre.getTypeC());
//        System.out.println("la capcité par chambre est "+getCapaciteMaximaleSelonType(chambre.getTypeC()));
//      //  int capaciteMax= getCapaciteMaximaleSelonType(chambre.getTypeC());
//        return nombreResParChambre<capaciteMax;
//    }
//
//    }
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
