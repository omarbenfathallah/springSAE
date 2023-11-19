package com.example.omar.Repository;


import com.example.omar.Entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {
  //  @Query("select  u from Universite u where u.nomUniversite=:nom ")
    Universite  findByNomUniversite(String nomUniversite );

    Universite findUniversiteByFoyer_NomFoyer(String nomf);
}
