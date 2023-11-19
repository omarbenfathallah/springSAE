package com.example.omar.Controller;

import com.example.omar.Entity.Reservation;
import com.example.omar.Service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService=reservationService;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservation();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable long id){
        return  reservationService.getReservationById(id);
    }

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation){
        return  reservationService.createReservation(reservation);
    }

    @PutMapping("/update/{id}")
    public Reservation updateReservation(@RequestBody Reservation reservation,@PathVariable long id){
        return  reservationService.updateReservation(reservation,id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteReservation(@PathVariable long id){
        reservationService.deleteReservation(id);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> ajouterReservation(
            @RequestParam("idChambre") long idChambre,
            @RequestParam("cinEtudiant") long cinEtudiant) {

            Reservation nouvelleReservation = reservationService.ajouterReservation(idChambre, cinEtudiant);
            return new ResponseEntity<>(nouvelleReservation, HttpStatus.CREATED);

    }

}

