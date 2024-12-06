package net.suttonbm.aoc2024.day6;

import net.suttonbm.aoc2024.day6.model.Courtyard;
import net.suttonbm.aoc2024.day6.model.Guard;
import net.suttonbm.aoc2024.day6.service.CourtyardParserService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourtyardParserTest {

    private final CourtyardParserService parser = new CourtyardParserService();

    @Test
    void parseValidInput() throws IOException {
        Courtyard courtyard = parser.parse("/6/valid-input.txt");

        char[][] grid = courtyard.getGrid();
        List<Guard> guards = courtyard.getGuards();

        assertEquals(10, grid.length, "Grid should have 10 rows");
        assertEquals(10, grid[0].length, "Each row should have 10 columns");
        assertEquals(1, guards.size(), "There should be 1 guard detected");

        Guard guard = guards.get(0);
        assertEquals(6, guard.getRow());
        assertEquals(4, guard.getCol());
        assertEquals('^', guard.getDirection());
    }

    @Test
    void parseEmptyInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> parser.parse("/6/empty-input.txt"));
        assertTrue(exception.getMessage().contains("Input file is empty"), "Should throw exception for empty file");
    }

    @Test
    void parseInvalidInput() {
        Exception exception = assertThrows((IllegalArgumentException.class), () -> parser.parse("/6/invalid-input.txt"));
        assertTrue(exception.getMessage().contains("All rows must have the same length"), "Should throw exception for uneven rows");
    }

    @Test
    void parseFileNotFound() {
        Exception exception = assertThrows(IOException.class, () -> parser.parse("/6/nonexistent.txt"));
        assertTrue(exception.getMessage().contains("Resource not found"), "Should throw exception for missing file");
    }
}