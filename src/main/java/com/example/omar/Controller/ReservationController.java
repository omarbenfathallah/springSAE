package com.example.omar.Controller;

import com.example.omar.Entity.Reservation;
import com.example.omar.Service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @PutMapping("/reservations/{idChambre}/{cinEtudiant}")
    @ResponseBody
    public void addReservationn(@PathVariable("idChambre")long idChambre, @PathVariable("cinEtudiant") long cinEtudiant){
        reservationService.ajouterReservation(idChambre,cinEtudiant);

    }
    @PutMapping("/annulerRes/{cinEt}")
    @ResponseBody
    public Reservation annulerReservation(@PathVariable("cinEt") Long cinEt){
        Reservation reservation = reservationService.annulerReservation(cinEt);
        return reservation;
    }

    @GetMapping("/byAcademicYearAndUniversity")
    public List<Reservation> getReservationsByAcademicYearAndUniversity(
            @RequestParam("academicYear") @DateTimeFormat(pattern = "yyyy-MM-dd") Date academicYear,
            @RequestParam("universityName") String universityName) {
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(academicYear, universityName);
    }
}

