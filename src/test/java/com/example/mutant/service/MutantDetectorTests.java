package com.example.mutant.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTests {

    private final MutantDetector detector = new MutantDetector();

    @Test
    void givenMutantDna_shouldReturnTrue() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(detector.isMutant(dna));
    }

    @Test
    void givenHumanDna_shouldReturnFalse() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(detector.isMutant(dna));
    }

    @Test
    void givenSingleSequence_shouldReturnFalse() {
        String[] dna = {
                "AAGAGT",
                "CCTTGC",
                "TAGTGT",
                "AGTCGG",
                "GCAGCA",
                "TCACTG"
        };

        assertFalse(detector.isMutant(dna));
    }



    @Test
    void givenDiagonalSequence_shouldReturnTrue() {
        String[] dna = {
                "AATGGA",
                "CAATGC",
                "TTAAGT",
                "AGAAGG",
                "CCCATA",
                "TCACTG"
        };

        // Diagonal principal:
        // (0,0)=A, (1,1)=A, (2,2)=A, (3,3)=A → SECUENCIA de 4 A
        assertTrue(detector.isMutant(dna));
    }


    @Test
    void givenInvalidCharacters_shouldThrowException() {
        String[] dna = {
                "ATGCXA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "GCCCTA",
                "TCACTG"
        };

        assertThrows(IllegalArgumentException.class,
                () -> detector.isMutant(dna));
    }
    @Test
    void givenSecondaryDiagonalSequence_shouldReturnTrue() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGGAGG",
                "GCCCTA",
                "TCACTG"
        };

        assertTrue(detector.isMutant(dna));
    }
    @Test
    void givenNonSquareMatrix_shouldThrowException() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT"
                // Falta una fila para que sea 4x4
        };

        assertThrows(IllegalArgumentException.class,
                () -> detector.isMutant(dna));
    }


}
