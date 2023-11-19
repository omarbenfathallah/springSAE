package com.example.omar.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Universite {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idUniversite;
    private String nomUniversite;

    private String adresse;

    @OneToOne(mappedBy="universite")
    @JsonManagedReference
    @JsonIgnore
    @ToString.Exclude
    private Foyer foyer;


}
