package net.suttonbm.aoc2024.day12.model;

import lombok.Getter;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Getter
public class GardenRegion {
    Set<Point> plots = new HashSet<>();
    long numFences = 0;
    long numDiscountedFences = 0;
    String plant;

    public GardenRegion(String plant) {
        this.plant = plant;
    }

    public void addPlot(Point plot) {
        plots.add(plot);
    }

    public void addFence() {
        numFences++;
    }

    public void addDiscountedFences(int num) {
        numDiscountedFences += num;
    }

    public long getPrice() {
        return numFences * plots.size();
    }

    public long getDiscountedPrice() {
        return numDiscountedFences * plots.size();
    }
}
