package net.suttonbm.aoc2024.day19.model;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.sql.Array;
import java.util.*;

@Slf4j
public class TowelPuzzle {
    private Set<String> towels;
    private List<String> puzzles;
    private Map<String, Long> memo;

    public TowelPuzzle(List<String> towels, List<String> puzzles) {
        this.puzzles = puzzles;
        this.towels = new HashSet<>(towels);
        this.memo = new HashMap<>();
    }

    public long solve() {
        long solved = 0;
        for (String puzzle : puzzles) {
            long result = solve(puzzle);
            //log.info("Processed {}", puzzle);
            solved += result;
        }
        return solved;
    }


    public long solve(String puzzle) {
        if (memo.containsKey(puzzle)) {
            return memo.get(puzzle);
        }

        long result = 0;

        for (String towel : towels) {
            if (matchFirst(puzzle, towel)) {
                if (towel.equals(puzzle)) {
                    result += 1;
                } else {
                    result += solve(puzzle.substring(towel.length()));
                }
            }
        }

        memo.put(puzzle, result);
        return result;
    }

    private boolean matchFirst(String puzzle, String towel) {
        if (puzzle.length() < towel.length()) {
            return false;
        }
        return puzzle.substring(0, towel.length()).equals(towel);
    }
}
