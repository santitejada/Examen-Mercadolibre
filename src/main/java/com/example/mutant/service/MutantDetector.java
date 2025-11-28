package com.example.mutant.service;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MutantDetector {

    private static final int SEQ = 4;
    private static final Set<Character> ALLOWED = Set.of('A', 'T', 'C', 'G');

    public boolean isMutant(String[] dna) {
        validateInput(dna);
        char[][] m = toMatrix(dna);
        int n = m.length;

        int sequences = 0;

        // single pass: desde cada celda miro 4 direcciones
        int[][] directions = {
                {0, 1},   // derecha
                {1, 0},   // abajo
                {1, 1},   // diagonal principal ↘
                {1, -1}   // diagonal secundaria ↙
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] d : directions) {
                    if (hasSequenceFrom(m, i, j, d[0], d[1])) {
                        sequences++;
                        if (sequences > 1) {
                            return true; // early termination
                        }
                    }
                }
            }
        }

        return false;
    }

    // ====== helpers ======

    private void validateInput(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("ADN vacío o nulo");
        }

        int n = dna.length;

        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("La matriz debe ser NxN");
            }
            for (char c : row.toCharArray()) {
                if (!ALLOWED.contains(c)) { // validación con Set O(1)
                    throw new IllegalArgumentException("Caracter inválido: " + c);
                }
            }
        }
    }

    private char[][] toMatrix(String[] dna) {
        int n = dna.length;
        char[][] matrix = new char[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = dna[i].toCharArray();
        }
        return matrix;
    }

    private boolean hasSequenceFrom(char[][] m, int i, int j, int di, int dj) {
        int n = m.length;

        int endRow = i + (SEQ - 1) * di;
        int endCol = j + (SEQ - 1) * dj;

        if (endRow < 0 || endRow >= n || endCol < 0 || endCol >= n) {
            return false; // se sale de la matriz
        }

        char c = m[i][j];

        for (int k = 1; k < SEQ; k++) {
            if (m[i + k * di][j + k * dj] != c) {
                return false;
            }
        }
        return true;
    }
}
