package net.suttonbm.aoc2024.day4;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import net.suttonbm.aoc2024.day4.service.PuzzleService;
import net.suttonbm.aoc2024.day4.service.PuzzleServiceImpl;
import net.suttonbm.aoc2024.day4.util.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PuzzleIntegrationTest {
    PuzzleServiceImpl service = new PuzzleServiceImpl();

    ResourceReader resourceReader = new ResourceReader();

    @Test
    void examplePuzzleTest() {
        PuzzleMatrix input = new PuzzleMatrix(
                resourceReader.convertToMatrix(
                        List.of(
                                "MMMSXXMASM",
                                "MSAMXMSMSA",
                                "AMXSXMAAMM",
                                "MSAMASMSMX",
                                "XMASAMXAMM",
                                "XXAMMXXAMA",
                                "SMSMSASXSS",
                                "SAXAMASAAA",
                                "MAMMMXMMMM",
                                "MXMXAXMASX")
                )
        );

        List<Match> matches = service.findAllMatches(input, "XMAS");
        service.printPuzzleMatrix(input, matches, "XMAS");

        assertEquals(18, service.analyzePattern(input, "XMAS"));
    }
}