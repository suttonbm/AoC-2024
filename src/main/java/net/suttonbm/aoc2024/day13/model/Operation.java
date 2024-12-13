package net.suttonbm.aoc2024.day13.model;

import lombok.Getter;

import java.awt.Point;

@Getter
public class Operation {
    private int x;
    private int y;

    public Operation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point apply(Point start) {
        return new Point(start.x + x, start.y + y);
    }
}
