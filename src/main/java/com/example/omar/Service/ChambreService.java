package com.example.omar.Service;

import com.example.omar.Entity.Chambre;
import com.example.omar.Entity.Reservation;
import com.example.omar.Entity.TypeChambre;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface ChambreService {
     Chambre createChambre(Chambre chambre);
     List<Chambre> getAllChambre();
     Optional<Chambre> getChambreById (long id);
     Chambre updateChambre(Chambre chambre,long id);
     void deleteChambre(long id);
     List<Chambre> getChambresParNomUniversite(String nomUniversite);
     List<Chambre> getChambresParBlocEtType (long idBloc, TypeChambre typeC) ;

     List<Chambre> getChambreParBlocEtType (long idBloc, TypeChambre typeC) ;

      List<Chambre> getChambresNonReserveesCetteAnnee(Date  reservation) ;

}
