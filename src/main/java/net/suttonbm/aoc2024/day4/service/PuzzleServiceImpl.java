package net.suttonbm.aoc2024.day4.service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day4.strategy.HorizontalSearchStrategy;
import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import net.suttonbm.aoc2024.day4.strategy.SearchStrategy;
import net.suttonbm.aoc2024.day4.strategy.DiagonalSearchStrategy;
import net.suttonbm.aoc2024.day4.strategy.VerticalSearchStrategy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PuzzleServiceImpl implements PuzzleService {
    private final List<SearchStrategy> strategies = new ArrayList<>();

    public PuzzleServiceImpl() {
        strategies.add(new HorizontalSearchStrategy());
        strategies.add(new VerticalSearchStrategy());
        strategies.add(new DiagonalSearchStrategy());
    }

    @Override
    public int analyzePattern(PuzzleMatrix matrix, String pattern) {
        int totalMatches = 0;
        for (SearchStrategy strategy : strategies) {
            int strategyMatches = strategy.findMatches(matrix, pattern).size();
            log.debug("Strategy {} found {} matches.", strategy, strategyMatches);
            totalMatches += strategyMatches;
        }
        return totalMatches;
    }

    @Override
    public List<Match> findAllMatches(PuzzleMatrix matrix, String pattern) {
        List<Match> allMatches = new ArrayList<>();
        for (SearchStrategy strategy : strategies) {
            allMatches.addAll(strategy.findMatches(matrix, pattern));
        }
        return allMatches;
    }

    public void printPuzzleMatrix(PuzzleMatrix matrix, List<Match> matches, String pattern) {
        Set<Point> matchedPositions = matches.stream()
                .flatMap(m -> IntStream.range(0, pattern.length())
                        .mapToObj(i -> new Point(m.getRow(), m.getCol() + i)))
                .collect(Collectors.toSet());

        for (int row = 0; row < matrix.getRows(); row++) {
            for (int col = 0; col < matrix.getColumns(); col++) {
                if (matchedPositions.contains(new Point(row, col))) {
                    System.out.print(matrix.getChar(row, col));
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}