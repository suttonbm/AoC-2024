package net.suttonbm.aoc2024.day25.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Keyhole {
    private final boolean[][] map;

    public boolean fits(Key key) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] && key.heights()[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean[][] heights() {
        return map;
    }
}