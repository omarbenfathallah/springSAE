package com.example.omar.Repository;

import com.example.omar.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    Etudiant findEtudiantByCin(Long cin);
}
