package com.example.omar.Service;

import com.example.omar.Entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReservationService {
     List<Reservation> getAllReservation();
     Optional<Reservation> getReservationById(long id);
     Reservation createReservation(Reservation reservation);
     Reservation updateReservation(Reservation  reservation,long id);
     void deleteReservation(long id);
      void ajouterReservation (long idChambre, long cinEtudiant) ;
      public Reservation annulerReservation(long cinEtudiant);
}
