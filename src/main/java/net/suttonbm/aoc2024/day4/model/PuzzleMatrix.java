package net.suttonbm.aoc2024.day4.model;

import java.util.ArrayList;
import java.util.List;

public class PuzzleMatrix {
    private final char[][] matrix;

    public PuzzleMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    public char getChar(int row, int col) {
        return matrix[row][col];
    }

    public char[] getRow(int row) {
        return matrix[row];
    }

    public char[] getColumn(int col) {
        char[] column = new char[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            column[i] = matrix[i][col];
        }
        return column;
    }

    public List<char[]> getDiagonals() {
        List<char[]> diagonals = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Upward diagonals
        for (int i = 0; i < rows + cols - 1; i++) {
            int startRow = i < cols ? 0 : i - cols + 1;
            int startCol = i < cols ? i : cols - 1;
            int length = Math.min(rows - startRow, cols - startCol);
            char[] diagonal = new char[length];

            for (int j = 0; j < length; j++) {
                if (isValidPosition(startRow + j, startCol - j)) {
                    diagonal[j] = matrix[startRow + j][startCol - j];
                } else {
                    diagonal[j] = '\0';
                }
            }

            diagonals.add(diagonal);
        }

        // Downward diagonals
        for (int i = 0; i < rows + cols - 1; i++) {
            int startRow = i < rows ? i : rows - 1;
            int startCol = i < rows ? 0 : i - rows + 1;
            int length = Math.min(startRow + 1, cols - startCol);
            char[] diagonal = new char[length];

            for (int j = 0; j < length; j++) {
                if (isValidPosition(startRow - j, startCol + j)) {
                    diagonal[j] = matrix[startRow - j][startCol + j];
                } else {
                    diagonal[j] = '\0';
                }
            }

            diagonals.add(diagonal);
        }

        return diagonals;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }
}