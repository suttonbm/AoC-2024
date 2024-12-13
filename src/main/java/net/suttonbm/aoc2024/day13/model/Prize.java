package net.suttonbm.aoc2024.day13.model;

public class Prize {
    public final long x;
    public final long y;
    public Prize(long x, long y) {
        this.x = x + 10000000000000L;
        this.y = y + 10000000000000L;
    }
}
