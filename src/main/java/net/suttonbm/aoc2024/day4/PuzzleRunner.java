package net.suttonbm.aoc2024.day4;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import net.suttonbm.aoc2024.day4.service.PuzzleServiceImpl;
import net.suttonbm.aoc2024.day4.service.PuzzleServiceSubmatrixImpl;
import net.suttonbm.aoc2024.day4.util.ResourceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PuzzleRunner implements CommandLineRunner {
    private final PuzzleServiceImpl puzzleService;
    private final PuzzleServiceSubmatrixImpl puzzleServiceSubmatrixImpl;
    private final ResourceReader resourceReader;

    @Override
    public void run(String... args) {
        PuzzleMatrix matrix = resourceReader.readPuzzle("4/input.txt");
        String pattern = "XMAS";

        int totalMatches = puzzleService.analyzePattern(matrix, pattern);
        log.info("Total matches: {}", totalMatches);

        int totalSubmatrixMatches = puzzleServiceSubmatrixImpl.analyzePattern(matrix, "M\0S,\0A\0,M\0S");
        log.info("Total submatrix matches: {}", totalSubmatrixMatches);
    }
}