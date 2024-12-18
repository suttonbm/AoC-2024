package net.suttonbm.aoc2024.day18.model;

import java.awt.*;

public enum Direction {
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
