package net.suttonbm.aoc2024.day25.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Key {
    private final boolean[][] map;

    public boolean[][] heights() {
        return map;
    }
}