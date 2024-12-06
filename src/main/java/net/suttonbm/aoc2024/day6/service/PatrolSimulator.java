package net.suttonbm.aoc2024.day6.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day6.model.Courtyard;
import net.suttonbm.aoc2024.day6.model.Guard;
import net.suttonbm.aoc2024.day6.model.SimulationResult;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PatrolSimulator {
    public SimulationResult simulateWithAnalysis(Courtyard courtyard) {
        char[][] grid = courtyard.getGrid();
        List<Guard> guards = courtyard.getGuards();
        Set<String> uniqueSpaces = new HashSet<>();

        // Include starting spaces for all guards
        for (Guard guard : guards) {
            uniqueSpaces.add(guard.getRow() + "," + guard.getCol());
        }

        simulateGuardMovement(grid, guards, uniqueSpaces, false);

        // Analyze loop-inducing placements
        Set<String> loopInducingPlacements = findLoopInducingPlacements(courtyard);

        log.debug("Simulation complete. Total unique spaces occupied: {}", uniqueSpaces.size());
        log.debug("Loop-inducing placements found: {}", loopInducingPlacements.size());

        return new SimulationResult(uniqueSpaces.size(), loopInducingPlacements);
    }

    private void simulateGuardMovement(char[][] grid, List<Guard> originalGuards, Set<String> uniqueSpaces, boolean logging) {
        // Simulate normal guard movement
        int tick = 0;
        List<Guard> guards = new ArrayList<>(originalGuards);
        while (!guards.isEmpty()) {
            Iterator<Guard> iterator = guards.iterator();
            List<Guard> updatedGuards = new ArrayList<>();
            while (iterator.hasNext()) {
                Guard guard = iterator.next();
                Guard updatedGuard = processGuardMovement(grid, guard, uniqueSpaces);

                if (updatedGuard == null) {
                    iterator.remove(); // Guard exited
                } else {
                    iterator.remove();
                    updatedGuards.add(updatedGuard); // Replace with updated guard
                }
            }
            guards = updatedGuards;
            if (logging) {
                printCourtyardState(grid, guards);
            }
        }
    }

    private Guard processGuardMovement(char[][] grid, Guard guard, Set<String> uniqueSpaces) {
        int row = guard.getRow();
        int col = guard.getCol();
        char direction = guard.getDirection();

        int[] newCoords = calculateNewCoordinates(row, col, direction);
        int newRow = newCoords[0];
        int newCol = newCoords[1];

        if (isOutsideGrid(grid, newRow, newCol)) {
            grid[row][col] = '.'; // Guard exits, leaving a blank space
            return null; // Guard has exited the courtyard
        }

        if (grid[newRow][newCol] == '.') {
            // Move guard
            grid[row][col] = '.'; // Clear old position
            grid[newRow][newCol] = direction; // Update new position
            uniqueSpaces.add(newRow + "," + newCol); // Track unique space
            return guard.moveTo(newRow, newCol); // Return updated guard
        }

        // Invalid move, rotate direction
        char newDirection = rotateDirection(direction);
        return guard.changeDirection(newDirection); // Return updated guard
    }

    private Set<String> findLoopInducingPlacements(Courtyard courtyard) {
        char[][] originalGrid = courtyard.getGrid();
        List<Guard> originalGuards = courtyard.getGuards();
        Set<String> visitedCoordinates = new HashSet<>();
        Set<String> loopInducingPlacements = new HashSet<>();

        simulateGuardMovement(originalGrid, originalGuards, visitedCoordinates, false);

        for (String coordinates : visitedCoordinates) {
            log.debug("Testing coordinate {}...", coordinates);
            String[] parts = coordinates.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (originalGrid[row][col] == '.') {
                char[][] tempGrid = deepCopyGrid(originalGrid);
                tempGrid[row][col] = '#';

                if (causesInfiniteLoop(tempGrid, originalGuards)) {
                    loopInducingPlacements.add("(" + row + "," + col + ")");
                }
            }
        }

        return loopInducingPlacements;
    }

    private boolean causesInfiniteLoop(char[][] grid, List<Guard> guards) {
        Set<String> visitedStates = new HashSet<>();
        List<Guard> tempGuards = deepCopyGuards(guards);

        for (int k = 0; k < 10_000; k++) { // Arbitrary tick limit to detect loops
            String currentState = generateGridState(grid, tempGuards);
            if (visitedStates.contains(currentState)) {
                return true; // Detected a loop
            }
            visitedStates.add(currentState);

            List<Guard> updatedGuards = new ArrayList<>();
            for (Guard guard : tempGuards) {
                Guard updatedGuard = processGuardMovement(grid, guard, new HashSet<>());
                if (updatedGuard != null) {
                    updatedGuards.add(updatedGuard);
                }
            }
            tempGuards = updatedGuards;

            if (tempGuards.isEmpty()) {
                break; // All guards exited
            }
        }

        return false; // No loop detected
    }

    private String generateGridState(char[][] grid, List<Guard> guards) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            sb.append(new String(row)).append("\n");
        }
        for (Guard guard : guards) {
            sb.append(guard).append("\n");
        }
        return sb.toString();
    }

    private char[][] deepCopyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    private List<Guard> deepCopyGuards(List<Guard> guards) {
        List<Guard> copy = new ArrayList<>();
        for (Guard guard : guards) {
            copy.add(new Guard(guard.getRow(), guard.getCol(), guard.getDirection()));
        }
        return copy;
    }

    private int[] calculateNewCoordinates(int row, int col, char direction) {
        switch (direction) {
            case '^': return new int[]{row - 1, col};
            case 'v': return new int[]{row + 1, col};
            case '<': return new int[]{row, col - 1};
            case '>': return new int[]{row, col + 1};
            default: throw new IllegalArgumentException("Invalid guard direction: " + direction);
        }
    }

    private boolean isOutsideGrid(char[][] grid, int row, int col) {
        return row < 0 || col < 0 || row >= grid.length || col >= grid[0].length;
    }

    private char rotateDirection(char direction) {
        switch (direction) {
            case '^': return '>';
            case '>': return 'v';
            case 'v': return '<';
            case '<': return '^';
            default: throw new IllegalArgumentException("Invalid guard direction: " + direction);
        }
    }

    private void printCourtyardState(char[][] grid, List<Guard> guards) {
        // Create a deep copy of the grid to overlay the guards
        char[][] gridCopy = deepCopyGrid(grid);

        // Overlay each guard on the grid copy
        for (Guard guard : guards) {
            gridCopy[guard.getRow()][guard.getCol()] = guard.getDirection();
        }

        // Build a string representation of the courtyard
        StringBuilder sb = new StringBuilder("Current Courtyard State:\n");
        for (char[] row : gridCopy) {
            sb.append(new String(row)).append("\n");
        }

        // Print or log the current state
        log.debug(sb.toString());
    }
}