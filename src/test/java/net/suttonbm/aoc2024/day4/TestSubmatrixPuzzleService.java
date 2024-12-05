package net.suttonbm.aoc2024.day4;

import net.suttonbm.aoc2024.day4.model.Match;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import net.suttonbm.aoc2024.day4.service.PuzzleServiceImpl;
import net.suttonbm.aoc2024.day4.service.PuzzleServiceSubmatrixImpl;
import net.suttonbm.aoc2024.day4.util.ResourceReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSubmatrixPuzzleService {
    PuzzleServiceSubmatrixImpl service = new PuzzleServiceSubmatrixImpl();

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

        assertEquals(9, service.analyzePattern(input, "M\0S,\0A\0,M\0S"));
    }
}
