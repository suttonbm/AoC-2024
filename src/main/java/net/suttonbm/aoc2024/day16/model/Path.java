package net.suttonbm.aoc2024.day16.model;

import java.util.Arrays;
import java.util.Objects;
import java.awt.Point;
import java.util.List;

public record Path(String path, long cost, List<Point> occupied) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Path path1 = (Path) o;
        return cost == path1.cost && Objects.equals(path, path1.path);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(path);
        return result;
    }
}
