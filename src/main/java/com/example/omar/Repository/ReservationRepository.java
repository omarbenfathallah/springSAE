package com.example.omar.Repository;

import com.example.omar.Entity.Etudiant;
import com.example.omar.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
//   @Query("SELECT COUNT(r) FROM Reservation r WHERE r.chambre.idChambre = :idChambre")
//   int countByChambreIdChambre(@Param("idChambre") long idChambre);
//
//   @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Reservation r WHERE r.chambre.idChambre = :idChambre AND :etudiantId MEMBER OF r.etudiant")
//   boolean existsByChambreIdChambreAndEtudiantIdEtudiant(@Param("idChambre") long idChambre, @Param("etudiantId") long etudiantId);

    Reservation findDistinctFirstByEtudiant(Etudiant etudiant);

//    @Query("SELECT c FROM Chambre c LEFT JOIN c.Reservation r WHERE r.idReservation IS NULL OR r.anneeUniversitaire != :annee")
//    List<Reservation> getReservationByAnneeReservation(Date date);
//
}
