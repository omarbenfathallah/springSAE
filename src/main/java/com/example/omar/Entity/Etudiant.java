package com.example.omar.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long idEtudiant;
    private  String nomEt;
    private String preniomEt;
    private long cin;
    private String ecole;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @JsonIgnore
    @JsonBackReference
    @ManyToMany(mappedBy = "etudiant",cascade = CascadeType.ALL)
    private Set<Reservation> reservation;


}

