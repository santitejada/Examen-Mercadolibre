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
                "AAAAGT",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "GCCCTA",
                "TCACTG"
        };
        assertFalse(detector.isMutant(dna));
    }

    @Test
    void givenDiagonalSequence_shouldReturnTrue() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATGT",
                "AGTAGG",
                "GCCCTA",
                "TCACTG"
        };
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
}
