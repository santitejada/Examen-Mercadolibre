package com.example.mutant.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

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
    void givenInvalidMatrix_shouldThrowException() {
        String[] dna = {
                "ATGC",
                "CAGT",
                "TTAT"
        };
        assertThrows(IllegalArgumentException.class,
                () -> detector.isMutant(dna));
    }
}
