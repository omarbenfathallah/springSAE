package com.example.omar.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Reservation {
    @Id
    private String idReservation;
    @Temporal(TemporalType.DATE)
    private Date anneeReservation;
    private boolean estValide;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,mappedBy="reservation")
    @JsonManagedReference
    @ToString.Exclude
    private Set<Etudiant>etudiant;


}
