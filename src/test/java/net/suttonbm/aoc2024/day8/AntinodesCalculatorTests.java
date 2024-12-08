package net.suttonbm.aoc2024.day8;

import net.suttonbm.aoc2024.day8.service.AntinodesCalculator;
import net.suttonbm.aoc2024.day8.service.InputFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AntinodesCalculatorTests {

    private final AntinodesCalculator antinodesCalculator = new AntinodesCalculator();
    private final InputFileParser inputFileParser = new InputFileParser();

    @Test
    void testCalculateUniqueAntinodes_EmptyRegion() {
        char[][] region = {
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'},
                {'.', '.', '.', '.'}
        };
        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        Assertions.assertEquals(0, uniqueAntinodes);
    }

    @Test
    void testCalculateUniqueAntinodes_SingleAntennaSet() {
        char[][] region = {
                {'.', '.', '.', '.'},
                {'.', 'A', 'A', '.'},
                {'.', 'A', 'A', '.'},
                {'.', '.', '.', '.'}
        };
        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        Assertions.assertEquals(12, uniqueAntinodes);
    }

    @Test
    void testCalculateUniqueAntinodes_MultipleSets() {
        char[][] region = {
                {'.', '.', '.', '.', '.', '.'},
                {'.', 'A', 'A', '.', 'B', '.'},
                {'.', 'A', 'A', '.', 'B', '.'},
                {'.', '.', '.', '.', 'B', '.'},
                {'.', '.', '.', '.', '.', '.'}
        };
        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        Assertions.assertEquals(14, uniqueAntinodes);
    }

    @Test
    void testCalculateUniqueAntinodes_IgnoreAntennaLocations() {
        char[][] region = {
                {'.', '.', '.', '.'},
                {'.', 'A', 'A', '.'},
                {'.', 'A', 'A', '.'},
                {'.', 'A', '.', '.'}
        };
        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        Assertions.assertEquals(11, uniqueAntinodes);
    }

    @Test
    void testGivenTestCase() {
        char[][] region = inputFileParser.parseInputFile("8/example.txt");

        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        Assertions.assertEquals(14, uniqueAntinodes);
    }
}