package net.suttonbm.aoc2024.day20.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class RaceMap {
    private boolean[][] walls;
    private Point start;
    private Point end;
    private Map<Point, Integer> path;
    private List<Cheat> cheats;

    private record Cheat(Point a, Point b, Integer saved) {};

    public RaceMap(boolean[][] walls, Point start, Point end) {
        this.walls = walls;
        this.start = start;
        this.end = end;
        initializePath();
    }

    private void initializePath() {
        path = new HashMap<Point, Integer>();

        int currentCost = 0;
        Point currentPoint = start;
        path.put(currentPoint, 0);

        while (!currentPoint.equals(end)) {
            for (Directions d : Directions.values()) {
                Point next = d.get(currentPoint, 1);
                if (!walls[next.x][next.y] && !path.containsKey(next)) {
                    currentCost++;
                    currentPoint = next;
                    path.put(currentPoint, currentCost);
                }
            }
        }
    }

    public List<Integer> getCheats() {
        this.cheats = new ArrayList<Cheat>();

        for (Point p : path.keySet()) {
            for (Directions d : Directions.values()) {
                Point candidate = d.get(p, 2);
                if (path.containsKey(candidate)) {
                    int thisScore = path.get(p);
                    int thatScore = path.get(candidate);
                    if (thatScore - thisScore - 2 > 0) {
                        cheats.add(new Cheat(p, candidate, thatScore - thisScore - 2));
                    }
                }
            }
        }

        return cheats.stream().mapToInt(Cheat::saved).boxed().sorted().collect(Collectors.toList());
    }

    public List<Integer> getBetterCheats() {
        this.cheats = new ArrayList<Cheat>();

        for (Point p : path.keySet()) {
            for (Point q: path.keySet()) {
                int dist = getDistance(p, q);
                if (dist > 20 || path.get(q) - path.get(p) - dist <= 0) {
                    continue;
                }
                cheats.add(new Cheat(p, q, path.get(q) - path.get(p) - dist));
            }
        }

        return cheats.stream().mapToInt(Cheat::saved).boxed().sorted().collect(Collectors.toList());
    }

    private int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
