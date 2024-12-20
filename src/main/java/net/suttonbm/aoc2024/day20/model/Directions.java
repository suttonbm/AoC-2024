package net.suttonbm.aoc2024.day20.model;

import java.awt.*;

public enum Directions {
    NORTH {
        @Override
        public Point get(Point from, int dist) {
            return new Point(from.x - dist, from.y);
        }
    },
    SOUTH {
        @Override
        public Point get(Point from, int dist) {
            return new Point(from.x + dist, from.y);
        }
    },
    EAST {
        @Override
        public Point get(Point from, int dist) {
            return new Point(from.x, from.y - dist);
        }
    },
    WEST {
        @Override
        public Point get(Point from, int dist) {
            return new Point(from.x, from.y + dist);
        }
    };

    public abstract Point get(Point from, int dist);
}
