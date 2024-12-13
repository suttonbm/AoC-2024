package net.suttonbm.aoc2024.day12.model;

import lombok.Getter;

import java.awt.Point;
import java.util.*;

@Getter
public class Garden {
    char[][] data;
    Set<Point> plots = new HashSet<>();
    Map<Integer, GardenRegion> regions = new HashMap<>();
    Integer currentRegion = 0;

    public Garden(char[][] data) {
        this.data = data;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Point plot = new Point(i, j);
                if (!plots.contains(plot)) {
                    regions.put(currentRegion, new GardenRegion(String.valueOf(data[i][j])));
                    addPlotAt(plot);
                    currentRegion++;
                }
            }
        }
    }

    private void addPlotAt(Point point) {
        if (plots.contains(point)) {
            return;
        }

        // Add to list of plots
        plots.add(point);

        // Add plot to current region
        regions.get(currentRegion).addPlot(point);

        int borders = 0;
        for (GardenDirections direction : GardenDirections.values()) {
            Point next = direction.get(point);
            if (isOutsideGarden(next)) {
                regions.get(currentRegion).addFence();
                borders++;
                continue;
            }
            if (data[point.x][point.y] == data[next.x][next.y]) {
                addPlotAt(next);
            } else {
                regions.get(currentRegion).addFence();
                borders++;
            }
        }
    }

    private boolean isOutsideGarden(Point point) {
        if (point.x >= data.length
                || point.y >= data[0].length
                || point.x < 0
                || point.y < 0) {
            return true;
        }
        return false;
    }

    public long getFenceCost() {
        long cost = 0;
        for (GardenRegion region : regions.values()) {
            cost += region.getPrice();
        }
        return cost;
    }

    public long getDiscountedCost() {
        long cost = 0;
        for (GardenRegion region : regions.values()) {
            cost += region.getDiscountedPrice();
        }
        return cost;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                sb.append(data[i][j]);
            }
            sb.append("\n");
        }
        sb.append("\n");

        for (GardenRegion region : regions.values()) {
            sb.append(region.getPlant());
            sb.append(": ");
            sb.append(region.getPlots().size());
            sb.append(" plots, ");
            sb.append(region.getNumDiscountedFences());
            sb.append(" discounted fences\n");
        }

        return sb.toString();
    }
}
