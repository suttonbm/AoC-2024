package net.suttonbm.aoc2024.day4.strategy;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.ArrayList;
import java.util.List;

public class VerticalSearchStrategy implements SearchStrategy {
    @Override
    public List<Match> findMatches(PuzzleMatrix matrix, String pattern) {
        List<Match> matches = new ArrayList<>();

        for (int col = 0; col < matrix.getColumns(); col++) {
            char[] colData = matrix.getColumn(col);
            // Forward search (top to bottom)
            for (int row = 0; row <= colData.length - pattern.length(); row++) {
                if (matchesVertically(matrix, row, col, pattern)) {
                    matches.add(new Match(row, col, SearchDirection.VERTICAL, null));
                }
            }
            // Reverse search (bottom to top)
            String reversed = new StringBuilder(pattern).reverse().toString();
            for (int row = 0; row <= colData.length - pattern.length(); row++) {
                if (matchesVertically(matrix, row, col, reversed)) {
                    matches.add(new Match(row, col, SearchDirection.VERTICAL_REVERSE, null));
                }
            }
        }
        return matches;
    }

    private boolean matchesVertically(PuzzleMatrix matrix, int startRow, int col,
                                      String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            int row = startRow + i;
            if (matrix.getChar(row, col) != pattern.charAt(i)) return false;
        }
        return true;
    }

    @Override
    public boolean supportsDirection(SearchDirection direction) {
        return direction == SearchDirection.VERTICAL || direction == SearchDirection.VERTICAL_REVERSE;
    }
}