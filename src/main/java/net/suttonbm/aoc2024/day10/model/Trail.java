package net.suttonbm.aoc2024.day10.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Trail {
    private int score;
    private int rating;
    private Point trailHead;
    private List<Point> summits;

    public Trail(List<Point> trailMap, Point trailHead) {
        this.rating = trailMap.size();
        this.trailHead = trailHead;

        Set<Point> set = new HashSet<Point>(trailMap);
        this.score = set.size();
        this.summits = new ArrayList<Point>(set);
    }
}
