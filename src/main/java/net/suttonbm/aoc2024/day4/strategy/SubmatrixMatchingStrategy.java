package net.suttonbm.aoc2024.day4.strategy;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SubmatrixMatchingStrategy implements SearchStrategy {
    @Override
    public List<Match> findMatches(PuzzleMatrix matrix, String pattern) {
        List<Match> matches = new ArrayList<>();
        char[][] patternArray = parsePattern(pattern);

        log.info("Sub-array pattern has {} rows and {} cols", patternArray.length, patternArray[0].length);
        for(int row = 0; row < patternArray.length; row++) {
            log.info(new String(patternArray[row]));
        }

        for (SubmatrixTransform direction : SubmatrixTransform.values()) {
            List<Match> newMatches = new ArrayList<>();
            char[][] thisPattern = direction.getPattern(patternArray);

            for (int row = 0; row < matrix.getRows(); row++) {
                for (int col = 0; col < matrix.getColumns(); col++) {
                    if (matchesPattern(matrix, row, col, thisPattern)) {
                        newMatches.add(new Match(row, col, null, direction));
                    }
                }
            }

            log.info("Transform {} found {} matches", direction.name(), newMatches.size());
            matches.addAll(newMatches);
        }

        return matches;
    }

    @Override
    public boolean supportsDirection(SearchDirection direction) {
        return true; // This strategy supports all directions
    }

    private boolean matchesPattern(PuzzleMatrix matrix, int startRow, int startCol, char[][] pattern) {
        int patternRows = pattern.length;
        int patternCols = pattern[0].length;

        for (int i = 0; i < patternRows; i++) {
            for (int j = 0; j < patternCols; j++) {
                int row = startRow + i;
                int col = startCol + j;

                if (!matrix.isValidPosition(row, col)) {
                    return false;
                } else if (pattern[i][j] != '\0' && pattern[i][j] != matrix.getChar(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }

    private char[][] parsePattern(String pattern) {
        String[] rows = pattern.split(",");
        char[][] patternArray = new char[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            patternArray[i] = rows[i].toCharArray();
        }

        return patternArray;
    }
}
