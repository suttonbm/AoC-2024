package net.suttonbm.aoc2024.day16.model;

import java.awt.*;
import java.util.Objects;

public record Reindeer(Point location, Direction direction) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Reindeer reindeer = (Reindeer) o;
        return Objects.equals(location, reindeer.location) && direction == reindeer.direction;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(location);
        result = 31 * result + Objects.hashCode(direction.name());
        return result;
    }

    public Point nextLocation() {
        Point next = null;
        switch (direction) {
            case NORTH:
                next = new Point(location.x, location.y-1);
                break;
            case SOUTH:
                next = new Point(location.x, location.y+1);
                break;
            case EAST:
                next = new Point(location.x+1, location.y);
                break;
            case WEST:
                next = new Point(location.x-1, location.y);
        }
        return next;
    }
}
