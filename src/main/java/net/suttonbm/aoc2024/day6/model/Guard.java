package net.suttonbm.aoc2024.day6.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Guard {

    private int row;
    private int col;
    private char direction;

    public Guard(int row, int col, char direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public Guard moveTo(int newRow, int newCol) {
        return new Guard(newRow, newCol, this.direction);
    }

    public Guard changeDirection(char newDirection) {
        return new Guard(this.row, this.col, newDirection);
    }

    @Override
    public String toString() {
        return String.format("Guard[row=%d, col=%d, direction=%c]", row, col, direction);
    }
}