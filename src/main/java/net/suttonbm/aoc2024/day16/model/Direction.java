package net.suttonbm.aoc2024.day16.model;

public enum Direction {
    EAST {
        public Direction right() {
            return Direction.SOUTH;
        }
        public Direction left() {
            return Direction.NORTH;
        }
    },
    WEST {
        public Direction right() {
            return Direction.NORTH;
        }
        public Direction left() {
            return Direction.SOUTH;
        }
    },
    NORTH {
        public Direction right() {
            return Direction.EAST;
        }
        public Direction left() {
            return Direction.WEST;
        }
    },
    SOUTH {
        public Direction right() {
            return Direction.WEST;
        }
        public Direction left() {
            return Direction.EAST;
        }
    };

    abstract public Direction right();
    abstract public Direction left();
}
