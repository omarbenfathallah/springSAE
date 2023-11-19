package com.example.omar.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long blocId ;
    private  String nomBloc ;
    private  String capaciteBloc ;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @ToString.Exclude
    Foyer foyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloc")
    @JsonManagedReference
    @JsonIgnore
    private Set<Chambre>chambre;

}

