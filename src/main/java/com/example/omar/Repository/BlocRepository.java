package com.example.omar.Repository;

import com.example.omar.Entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//zeydaa
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    @Query("SELECT b FROM Bloc b WHERE b.nomBloc = :nomBloc")
    Bloc findByNomBloc(@Param("nomBloc") String nomBloc);
}
