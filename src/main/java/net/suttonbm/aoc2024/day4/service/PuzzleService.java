package net.suttonbm.aoc2024.day4.service;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;

import java.util.List;

public interface PuzzleService {
    /**
     * Primary analysis method
     * @return total number of matches found
     */
    int analyzePattern(PuzzleMatrix matrix, String pattern);

    /**
     * Detailed match information
     * @return List of all matches found
     */
    List<Match> findAllMatches(PuzzleMatrix matrix, String pattern);
}