package net.suttonbm.aoc2024.day4.strategy;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.List;

public interface SearchStrategy {
    List<Match> findMatches(PuzzleMatrix matrix, String pattern);
    boolean supportsDirection(SearchDirection direction);
}


