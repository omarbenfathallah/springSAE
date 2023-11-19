package com.example.omar.Repository;

import com.example.omar.Entity.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    public Foyer getFoyerByNomFoyer(String nomFoyer);
}
