package net.suttonbm.aoc2024.day12.model;

import java.awt.Point;

public enum GardenDirections {
    NORTH {
        @Override
        public Point get(Point from) {
            return new Point(from.x - 1, from.y);
        }
    },
    SOUTH {
        @Override
        public Point get(Point from) {
            return new Point(from.x + 1, from.y);
        }
    },
    EAST {
        @Override
        public Point get(Point from) {
            return new Point(from.x, from.y - 1);
        }
    },
    WEST {
        @Override
        public Point get(Point from) {
            return new Point(from.x, from.y + 1);
        }
    };

    public abstract Point get(Point from);
}