package com.example.omar.Service;

import com.example.omar.Entity.Universite;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UniversiteService {
    Universite addUniversite(Universite universite);
    List<Universite> getAllUniversite();
    Universite getUniversiteById(long id);
    Universite updateUniversite(Universite universite,long id);
    void deleteUniversite(long id);
    Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) ;

    Universite desaffecterFoyerAUniversite(long idUniversite);
    Universite desaffecterFoyerAUniversite(long idFoyer, long idUniversitee);

  //  @Query("select u from Universite u and   ")

}
