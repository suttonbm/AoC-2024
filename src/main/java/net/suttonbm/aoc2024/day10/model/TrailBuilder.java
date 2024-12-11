package net.suttonbm.aoc2024.day10.model;

import java.util.*;
import java.awt.Point;

public class TrailBuilder {
    public static Optional<Trail> build(TopoMap map, Point trailHead) {
        if (map.heightAt(trailHead) != 0) {
            return Optional.empty();
        }

        List<Point> trailMap = getTrailMap(map, trailHead);

        if (trailMap.size() >= 0) {
            return Optional.of(new Trail(trailMap, trailHead));
        }
        return Optional.empty();
    }

    public static List<Point> getTrailMap(TopoMap map, Point here) {
        if (map.heightAt(here) == 9) {
            return List.of(here);
        }

        List<Point> peaks = new ArrayList<>();
        for (Directions direction : Directions.values()) {
            Point there = direction.get(here);
            if (map.isOnGrid(there)
                    && (map.heightAt(there) - map.heightAt(here)) == 1) {
                peaks.addAll(getTrailMap(map, there));
            }
        }

        return peaks;
    }

    public static int getTrailRating(TopoMap map, Point here) {
        if (map.heightAt(here) == 9) {
            return 1;
        }

        int rating = 0;
        for (Directions direction : Directions.values()) {
            Point there = direction.get(here);
            if (map.isOnGrid(there)
                    && (map.heightAt(there) - map.heightAt(here)) == 1) {
                rating += getTrailRating(map, there);
            }
        }

        return rating;
    }
}
