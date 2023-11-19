package com.example.omar.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Foyer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private  long idFoyer;
    private String nomFoyer ;
    private long capacityFoyer ;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonBackReference
    @ToString.Exclude
    private Universite universite;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foyer")
    @JsonManagedReference
    private Set<Bloc> bloc ;

}
