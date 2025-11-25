package com.example.mutant.service;

import org.springframework.stereotype.Service;

@Service
public class MutantDetector {

    private static final int SEQ = 4;

    public boolean isMutant(String[] dna) {
        validateInput(dna);
        char[][] matrix = toMatrix(dna);

        int sequences = 0;

        // horizontal
        sequences += countHorizontal(matrix);
        if (sequences > 1) return true;

        // vertical
        sequences += countVertical(matrix);
        if (sequences > 1) return true;

        // diagonales
        sequences += countDiagonal(matrix);

        return sequences > 1;
    }

    private void validateInput(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("ADN vacío o nulo");
        }

        int n = dna.length;

        for (String row : dna) {
            if (row == null || row.length() != n) {
                throw new IllegalArgumentException("La matriz debe ser NxN");
            }
            if (!row.matches("[ATCG]+")) {
                throw new IllegalArgumentException("Caracteres inválidos en ADN");
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

    private int countHorizontal(char[][] m) {
        int n = m.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - SEQ; j++) {
                if (allEqual(m[i][j], m[i][j + 1], m[i][j + 2], m[i][j + 3])) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countVertical(char[][] m) {
        int n = m.length;
        int count = 0;

        for (int i = 0; i <= n - SEQ; i++) {
            for (int j = 0; j < n; j++) {
                if (allEqual(m[i][j], m[i + 1][j], m[i + 2][j], m[i + 3][j])) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countDiagonal(char[][] m) {
        int n = m.length;
        int count = 0;

        // diagonales "\"
        for (int i = 0; i <= n - SEQ; i++) {
            for (int j = 0; j <= n - SEQ; j++) {
                if (allEqual(m[i][j], m[i + 1][j + 1], m[i + 2][j + 2], m[i + 3][j + 3])) {
                    count++;
                }
            }
        }

        // diagonales "/"
        for (int i = 0; i <= n - SEQ; i++) {
            for (int j = SEQ - 1; j < n; j++) {
                if (allEqual(m[i][j], m[i + 1][j - 1], m[i + 2][j - 2], m[i + 3][j - 3])) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean allEqual(char a, char b, char c, char d) {
        return a == b && b == c && c == d;
    }
}
