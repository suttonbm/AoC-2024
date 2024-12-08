package net.suttonbm.aoc2024.day8.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class AntinodesCalculator {

    public int calculateUniqueAntinodes(char[][] region) {
        Map<Character, Set<Coordinate>> antennaLocations = getAntennaLocations(region);
        log.debug("Initial Region:");
        printRegionWithAntinodes(region, new HashSet<Coordinate>(), antennaLocations);
        Set<Coordinate> antinodesLocations = calculateAntinodesLocations(antennaLocations, region.length, region[0].length);
        log.debug("Region with Antinodes and Antennas:");
        printRegionWithAntinodes(region, antinodesLocations, antennaLocations);
        return antinodesLocations.size();
    }

    private Map<Character, Set<Coordinate>> getAntennaLocations(char[][] region) {
        Map<Character, Set<Coordinate>> antennaLocations = new HashMap<>();
        for (int i = 0; i < region.length; i++) {
            for (int j = 0; j < region[0].length; j++) {
                char c = region[i][j];
                if (c != '.' && c != '\n') {
                    antennaLocations.computeIfAbsent(c, k -> new HashSet<>())
                            .add(new Coordinate(i, j));
                }
            }
        }
        return antennaLocations;
    }

    private Set<Coordinate> calculateAntinodesLocations(Map<Character, Set<Coordinate>> antennaLocations, int regionRows, int regionCols) {
        Set<Coordinate> antinodesLocations = new HashSet<>();
        for (Map.Entry<Character, Set<Coordinate>> entry : antennaLocations.entrySet()) {
            Set<Coordinate> antennas = entry.getValue();
            Coordinate[] antennaArray = antennas.toArray(new Coordinate[0]);
            for (int i = 0; i < antennaArray.length; i++) {
                for (int j = i + 1; j < antennaArray.length; j++) {
                    Coordinate a1 = antennaArray[i];
                    Coordinate a2 = antennaArray[j];
                    addAntinode(antinodesLocations, a1, a2, regionRows, regionCols);
                    addAntinode(antinodesLocations, a2, a1, regionRows, regionCols);
                }
            }
        }
        return antinodesLocations;
    }

    private Set<Coordinate> removeAntennaLocations(Set<Coordinate> antinodesLocations, Map<Character, Set<Coordinate>> antennaLocations) {
        Set<Coordinate> newLocations = new HashSet<Coordinate>(antinodesLocations);
        for (Set<Coordinate> antennas : antennaLocations.values()) {
            newLocations.removeAll(antennas);
        }
        return newLocations;
    }

    private void addAntinode(Set<Coordinate> antinodesLocations, Coordinate a1, Coordinate a2, int regionRows, int regionCols) {
        int rowDiff = a2.row - a1.row;
        int colDiff = a2.col - a1.col;

        int x = 0;
        Coordinate proposed = new Coordinate(a1.row - x * rowDiff, a1.col - x * colDiff);
        while (isWithinRegionBoundary(proposed, regionRows, regionCols)) {
            antinodesLocations.add(proposed);
            x++;
            proposed = new Coordinate(a1.row - x * rowDiff, a1.col - x * colDiff);
        }
    }

    private boolean isWithinRegionBoundary(Coordinate coordinate, int regionRows, int regionCols) {
        return coordinate.row >= 0 && coordinate.row < regionRows
                && coordinate.col >= 0 && coordinate.col < regionCols;
    }

    private void printRegionWithAntinodes(char[][] region, Set<Coordinate> antinodesLocations, Map<Character, Set<Coordinate>> antennaLocations) {
        char[][] regionWithAntinodes = new char[region.length][region[0].length];
        for (int i = 0; i < region.length; i++) {
            for (int j = 0; j < region[0].length; j++) {
                Coordinate coordinate = new Coordinate(i, j);
                regionWithAntinodes[i][j] = region[i][j];

                if (antinodesLocations.contains(coordinate)) {
                    regionWithAntinodes[i][j] = '#';
                }

                boolean antennaFound = false;
                for (Map.Entry<Character, Set<Coordinate>> entry : antennaLocations.entrySet()) {
                    if (entry.getValue().contains(coordinate)) {
                        regionWithAntinodes[i][j] = entry.getKey();
                        antennaFound = true;
                        break;
                    }
                }
            }
        }

        for (char[] row : regionWithAntinodes) {
            log.debug(new String(row));
        }
    }

    private record Coordinate(int row, int col) {
    }
}