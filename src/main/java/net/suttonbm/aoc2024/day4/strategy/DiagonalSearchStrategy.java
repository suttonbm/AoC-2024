package net.suttonbm.aoc2024.day4.strategy;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.ArrayList;
import java.util.List;

public class DiagonalSearchStrategy implements SearchStrategy {
    @Override
    public List<Match> findMatches(PuzzleMatrix matrix, String pattern) {
        List<Match> matches = new ArrayList<>();
        matches.addAll(findDiagonalMatches(matrix, pattern, "up"));
        matches.addAll(findDiagonalMatches(matrix, pattern, "down"));
        return matches;
    }

    private List<Match> findDiagonalMatches(PuzzleMatrix matrix, String pattern, String direction) {
        List<Match> matches = new ArrayList<>();
        // Check all possible diagonal starting positions
        for (int row = 0; row <= matrix.getRows(); row++) {
            for (int col = 0; col <= matrix.getColumns(); col++) {
                if (matchesDiagonally(matrix, row, col, pattern, direction == "down")) {
                    matches.add(new Match(row, col, SearchDirection.DIAGONAL, null));
                }
            }
        }
        // Check reverse pattern
        String reversed = new StringBuilder(pattern).reverse().toString();
        for (int row = 0; row <= matrix.getRows(); row++) {
            for (int col = 0; col <= matrix.getColumns(); col++) {
                if (matchesDiagonally(matrix, row, col, reversed, direction == "down")) {
                    matches.add(new Match(row, col, SearchDirection.DIAGONAL_REVERSE, null));
                }
            }
        }
        return matches;
    }

    private boolean matchesDiagonally(PuzzleMatrix matrix, int startRow, int startCol,
                                      String pattern, boolean down) {
        int rowIncrement = down ? 1 : -1;
        int colIncrement = 1;

        for (int i = 0; i < pattern.length(); i++) {
            int row = startRow + (i * rowIncrement);
            int col = startCol + (i * colIncrement);

            if (!matrix.isValidPosition(row, col) ||
                    matrix.getChar(row, col) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean supportsDirection(SearchDirection direction) {
        return direction == SearchDirection.DIAGONAL || direction == SearchDirection.DIAGONAL_REVERSE;
    }
}