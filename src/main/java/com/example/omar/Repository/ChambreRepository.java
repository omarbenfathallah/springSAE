package com.example.omar.Repository;

import com.example.omar.Entity.Chambre;
import com.example.omar.Entity.Reservation;
import com.example.omar.Entity.TypeChambre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {
    List<Chambre> findByBloc_Foyer_Universite_NomUniversite(String  nomU);
   // @Query("SELECT c from Chambre c where c.numeroChambre=:num")
    Chambre  findByNumeroChambre(Long  num);
    List<Chambre> findChambreByBloc_BlocIdAndTypeC(Long blocid , TypeChambre typeChambre);

    //@Query("SELECT c FROM Chambre c ,Bloc b WHERE b.blocId = :blocId AND c.typeC = :typeChambre")
    @Query("SELECT c from Chambre c,Bloc b where c.bloc.blocId =:blocId  AND c.typeC=:typeChambre ")
    List<Chambre> findChambreByBlocIdAndTypeC(@Param("blocId") Long blocId, @Param("typeChambre") TypeChambre typeChambre);

    Chambre findChambreByReservations(Reservation reservation);

}
