package net.suttonbm.aoc2024.day6.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Courtyard {

    private final char[][] grid;
    private final List<Guard> guards;

    public Courtyard(char[][] grid, List<Guard> guards) {
        this.grid = grid;
        this.guards = guards;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Courtyard:\n");
        for (char[] row : grid) {
            sb.append(String.valueOf(row)).append("\n");
        }
        sb.append("Guards: ").append(guards);
        return sb.toString();
    }
}