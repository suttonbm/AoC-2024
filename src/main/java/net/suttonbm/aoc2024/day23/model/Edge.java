package net.suttonbm.aoc2024.day23.model;

import java.util.Objects;

public record Edge (String a, String b) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Edge pair = (Edge) o;
        return (Objects.equals(a, pair.a) && Objects.equals(b, pair.b))
                || (Objects.equals(a, pair.b) && Objects.equals(b, pair.a));
    }

    @Override
    public int hashCode() {
        return Objects.hash(a) + Objects.hash(b);
    }
}