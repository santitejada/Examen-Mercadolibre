package com.example.mutant.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DnaRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 512)
    private String dnaHash;

    @Column(nullable = false)
    private boolean mutant;
}
