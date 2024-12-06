package net.suttonbm.aoc2024.day6;

import net.suttonbm.aoc2024.day6.model.Courtyard;
import net.suttonbm.aoc2024.day6.model.Guard;
import net.suttonbm.aoc2024.day6.model.SimulationResult;
import net.suttonbm.aoc2024.day6.service.CourtyardParserService;
import net.suttonbm.aoc2024.day6.service.PatrolSimulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PatrolSimulatorTest {

    private final PatrolSimulator simulator = new PatrolSimulator();
    private final CourtyardParserService parser = new CourtyardParserService();

    @Test
    void simulateSingleGuardExitsImmediately() {
        char[][] grid = {
                {'.', '.', '.'},
                {'.', '^', '.'},
                {'.', '.', '.'}
        };

        Guard guard = new Guard(1, 1, '^');
        Courtyard courtyard = new Courtyard(grid, Arrays.asList(guard));

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(2, result.getUniqueSpacesOccupied(), "Only two spaces should be occupied before the guard exits");
    }

    @Test
    void simulateGuardTurnsAndExits() {
        char[][] grid = {
                {'.', '.', '.'},
                {'#', '^', '#'},
                {'#', '.', '#'}
        };

        Guard guard = new Guard(1, 1, '^');
        Courtyard courtyard = new Courtyard(grid, Arrays.asList(guard));

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(2, result.getUniqueSpacesOccupied(), "Guard should visit two unique spaces before exiting");
    }

    @Test
    void simulateMultipleGuards() {
        char[][] grid = {
                {'.', '.', '.'},
                {'^', '.', 'v'},
                {'.', '.', '.'}
        };

        Guard guard1 = new Guard(1, 0, '^');
        Guard guard2 = new Guard(1, 2, 'v');
        Courtyard courtyard = new Courtyard(grid, Arrays.asList(guard1, guard2));

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(4, result.getUniqueSpacesOccupied(), "Both guards should visit four unique spaces in total");
    }

    @Test
    void simulateGridWithObstacles() {
        char[][] grid = {
                {'#', '#', '.'},
                {'.', '.', '#'},
                {'^', '#', '.'}
        };

        Guard guard1 = new Guard(2, 0, '^');
        Courtyard courtyard = new Courtyard(grid, Arrays.asList(guard1));

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(3, result.getUniqueSpacesOccupied(), "Obstacles should limit guard movement");
    }

    @Test
    void simulateGridWithLoopCheck() {
        char[][] grid = {
                {'#', '#', '.'},
                {'.', '>', '#'},
                {'.', '#', '.'}
        };

        Guard guard1 = new Guard(1, 1, '>');
        Courtyard courtyard = new Courtyard(grid, Arrays.asList(guard1));

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(2, result.getUniqueSpacesOccupied(), "Obstacles should limit guard movement");
        assertEquals(1, result.getLoopInducingPlacements().size(), "Only one loop inducing placement exists");
    }

    @Test
    void simulateLargerGridWithLoopCheck() throws IOException {
        Courtyard courtyard = parser.parse("/6/valid-input.txt");

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(41, result.getUniqueSpacesOccupied(), "Obstacles should limit guard movement");
        assertEquals(6, result.getLoopInducingPlacements().size(), "Only one loop inducing placement exists");
    }

    @Test
    void simulateNoGuards() {
        char[][] grid = {
                {'.', '.', '.'},
                {'.', '.', '.'},
                {'.', '.', '.'}
        };

        Courtyard courtyard = new Courtyard(grid, Arrays.asList());

        SimulationResult result = simulator.simulateWithAnalysis(courtyard);
        assertEquals(0, result.getUniqueSpacesOccupied(), "No guards means no spaces occupied");
    }
}