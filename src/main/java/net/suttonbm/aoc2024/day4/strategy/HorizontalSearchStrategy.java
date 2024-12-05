package net.suttonbm.aoc2024.day4.strategy;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.ArrayList;
import java.util.List;

public class HorizontalSearchStrategy implements SearchStrategy {
    @Override
    public List<Match> findMatches(PuzzleMatrix matrix, String pattern) {
        List<Match> matches = new ArrayList<>();

        for (int row = 0; row < matrix.getRows(); row++) {
            char[] rowData = matrix.getRow(row);
            // Forward search
            for (int col = 0; col <= rowData.length - pattern.length(); col++) {
                if (matchesAt(rowData, col, pattern, false)) {
                    matches.add(new Match(row, col, SearchDirection.HORIZONTAL, null));
                }
            }
            // Reverse search
            String reversed = new StringBuilder(pattern).reverse().toString();
            for (int col = 0; col <= rowData.length - pattern.length(); col++) {
                if (matchesAt(rowData, col, reversed, false)) {
                    matches.add(new Match(row, col, SearchDirection.HORIZONTAL_REVERSE, null));
                }
            }
        }
        return matches;
    }

    @Override
    public boolean supportsDirection(SearchDirection direction) {
        return direction == SearchDirection.HORIZONTAL || direction == SearchDirection.HORIZONTAL_REVERSE;
    }

    private boolean matchesAt(char[] data, int start, String pattern, boolean reverse) {
        for (int i = 0; i < pattern.length(); i++) {
            if (data[start + i] != pattern.charAt(i)) return false;
        }
        return true;
    }
}