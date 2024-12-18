package net.suttonbm.aoc2024.day18.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.awt.Point;

@Slf4j
public class MemoryPathSearch {
    private List<Point> corrupted;
    private boolean[][] map;
    private final Point start;
    private final Point end;

    public Point getBlockingPoint(int startFrom) {
        for (int i=startFrom; i<corrupted.size(); i++) {
            map[corrupted.get(i).x][corrupted.get(i).y] = true;
            int test = findBestPath();
            if (test == -1) {
                return corrupted.get(i);
            }
        }
        return new Point(-1, -1);
    }

    private record Node(Point p, int score) {
        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;
            return p.equals(node.p);
        }

        @Override
        public int hashCode() {
            return p.hashCode();
        }
    }

    public MemoryPathSearch(List<Point> corrupted, int mapSize, Point start, Point end) {
        this.corrupted = corrupted;
        this.map = new boolean[mapSize][mapSize];
        this.start = start;
        this.end = end;
    }

    public int getBestPathAt(int time) {
        for (int i = 0; i < time; i++) {
            Point p = corrupted.get(i);
            map[p.x][p.y] = true;
        }

        return findBestPath();
    }

    private int findBestPath() {
        Queue<Node> queue = new PriorityQueue<>(2 * map.length, Comparator.comparingInt(o -> o.score));
        queue.add(new Node(start, 0));

        Map<Point, Point> from = new HashMap<>();

        Map<Point, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.p.x == end.x && node.p.y == end.y) {
                List<Point> path = new ArrayList<>();
                Point p = node.p;
                path.add(p);
                while (from.containsKey(p)) {
                    p = from.get(p);
                    path.add(0, p);
                }
                return path.size() - 1;
            }

            for (Direction d : Direction.values()) {
                Point next = d.get(node.p);

                if (!isInBounds(next) || map[next.x][next.y]) {
                    continue;
                }

                int newG = gScore.get(node.p) + 1;
                if (newG < gScore.getOrDefault(next, Integer.MAX_VALUE)) {
                    from.put(next, node.p);
                    gScore.put(next, newG);
                    if (!queue.contains(new Node(next, newG))) {
                        queue.add(new Node(next, newG));
                    }
                }
            }
        }

        return -1;
    }

    private boolean isInBounds(Point p) {
        return p.x >= 0 && p.x < map.length && p.y >= 0 && p.y < map[0].length;
    }
}
