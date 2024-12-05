package net.suttonbm.aoc2024.day4.service;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import net.suttonbm.aoc2024.day4.strategy.SearchStrategy;
import net.suttonbm.aoc2024.day4.strategy.SubmatrixMatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PuzzleServiceSubmatrixImpl implements PuzzleService {
    private final SearchStrategy searchStrategy;

    public PuzzleServiceSubmatrixImpl() {
        this.searchStrategy = new SubmatrixMatchingStrategy();
    }

    @Override
    public int analyzePattern(PuzzleMatrix matrix, String pattern) {
        return searchStrategy.findMatches(matrix, pattern).size();
    }

    @Override
    public List<Match> findAllMatches(PuzzleMatrix matrix, String pattern) {
        return searchStrategy.findMatches(matrix, pattern);
    }
}