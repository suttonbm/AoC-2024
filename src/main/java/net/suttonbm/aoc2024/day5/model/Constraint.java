package net.suttonbm.aoc2024.day5.model;

public class Constraint {
    private final int first;
    private final int second;

    public Constraint(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }


    public int getSecond() {
        return second;
    }
}