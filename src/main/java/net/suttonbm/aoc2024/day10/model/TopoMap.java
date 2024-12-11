package net.suttonbm.aoc2024.day10.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@AllArgsConstructor
@Getter
public class TopoMap {
    private int[][] grid;

    public int rows() {
        return grid.length;
    }

    public int cols() {
        return grid[0].length;
    }

    public int heightAt(Point point) {
        return grid[point.x][point.y];
    }

    public boolean isOnGrid(Point point) {
        return point.x < grid.length && point.x >= 0
                && point.y < grid[0].length && point.y >= 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                builder.append(grid[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
