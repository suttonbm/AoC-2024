package net.suttonbm.aoc2024.day24.model;

import lombok.Getter;

public class Wire {
    private boolean state;
    private boolean hasFired;
    @Getter
    private final String name;

    public Wire(String name) {
        this.name = name;
        state = false;
        hasFired = false;
    }

    public boolean hasFired() {
        return hasFired;
    }

    public void fire(boolean b) {
        state = b;
        hasFired = true;
    }

    public boolean val() {
        return state;
    }
}