package net.suttonbm.aoc2024.day16.model;

import lombok.Getter;

import java.awt.Point;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Maze {
    private final boolean[][] walls;
    private final Reindeer reindeer;
    private final Point finish;

    private Map<Path, Reindeer> heads = new HashMap<>();
    private List<Path> bestPaths = new ArrayList<>();
    private long bestScore = Long.MAX_VALUE;

    public Maze(boolean[][] walls, Reindeer deer, Point finish) {
        this.walls = walls;
        this.reindeer = deer;
        this.finish = finish;
    }

    public void run() {
        boolean[][] initialVisited = new boolean[walls.length][walls[0].length];
        initialVisited[reindeer.location().x][reindeer.location().y] = true;
        Path initialPath = new Path(".", 0L, new ArrayList<>());
        heads.put(initialPath, reindeer);

        // int iteration = 0;
        while (true) {
            Path bestHead = getLowestScoreHead();
            // System.out.println("Iteration " + iteration + ": Score " + bestHead.cost());
            // printPath(bestHead.visited());
            findBestPath(heads.get(bestHead), bestHead);
            // iteration++;

            if (heads.isEmpty()) {
                break;
            }
        }
    }

    public long getCost() {
        return bestScore;
    }

    public int visitedInBestPaths() {
        boolean[][] visited = new boolean[walls.length][walls[0].length];
        for (Path p : bestPaths) {
            visited = doPathMap(p, visited);
        }

        int result = 0;
        for (int x = 0; x < walls.length; x++) {
            for (int y = 0; y < walls[0].length; y++) {
                result += visited[x][y] ? 1 : 0;
            }
        }
        return result;
    }

    private boolean[][] doPathMap(Path p, boolean[][] visited) {
        char[] moves = p.path().toCharArray();
        Reindeer tempDeer = new Reindeer(reindeer.location(), reindeer.direction());

        for (int i = 0; i < moves.length; i++) {
            switch (moves[i]) {
                case '.':
                    break;
                case 'M':
                    tempDeer = new Reindeer(tempDeer.nextLocation(), tempDeer.direction());
                    break;
                case 'L':
                    tempDeer = new Reindeer(tempDeer.location(), tempDeer.direction().left());
                    break;
                case 'R':
                    tempDeer = new Reindeer(tempDeer.location(), tempDeer.direction().right());
                    break;
            }

            visited[tempDeer.location().x][tempDeer.location().y] = true;
        }

        return visited;
    }

    private Path getLowestScoreHead() {
        Path best = null;
        long score = Long.MAX_VALUE;
        for (Path head : heads.keySet()) {
            if (head.cost() < score) {
                best = head;
                score = head.cost();
            }
        }
        return best;
    }

    private void findBestPath(Reindeer reindeer, Path path) {
        // If at the finish, quit.
        if (reindeer.location().equals(finish)) {
            if (bestPaths.isEmpty() || path.cost() < bestScore) {
                bestPaths = List.of(path);
                bestScore = path.cost();
            } else if (path.cost() == bestScore) {
                bestPaths = new ArrayList<>(bestPaths);
                bestPaths.add(path);
            }
            heads.remove(path);
            return;
        }

        // If another path has finished and this cost is higher, quit.
        if (path.cost() >= bestScore) {
            heads.remove(path);
            return;
        }

        // Avoid spinning in circles
        char lastMove = path.path().charAt(path.path().length()-1);
        if (lastMove != 'L' && lastMove != 'R') {
            Path turnedLeft = new Path(path.path() + "L", path.cost() + 1000, path.occupied());
            Path turnedRight = new Path(path.path() + "R", path.cost() + 1000, path.occupied());

            heads.put(turnedLeft, new Reindeer(reindeer.location(), reindeer.direction().left()));
            heads.put(turnedRight, new Reindeer(reindeer.location(), reindeer.direction().right()));
        }

        // If no wall, reindeer can move
        Point nextLoc = reindeer.nextLocation();
        if (!walls[nextLoc.x][nextLoc.y]
            && !path.occupied().contains(nextLoc)) {
            List<Point> newOccupied = new ArrayList<>(path.occupied());
            newOccupied.add(nextLoc);
            heads.put(new Path(path.path() + "M", path.cost() + 1, newOccupied),
                    new Reindeer(reindeer.nextLocation(), reindeer.direction()));
        }

        // Clean up after moving.
        heads.remove(path);
    }

    private char[][] buildString(boolean[][] path) {
        char[][] result = new char[walls.length][walls[0].length];
        for (int x = 0; x < walls.length; x++) {
            for (int y = 0; y < walls[0].length; y++) {
                if (walls[x][y]) {
                    result[x][y] = '#';
                } else if (reindeer.location().equals(new Point(x, y))) {
                    result[x][y] = 'S';
                } else if (finish.equals(new Point(x, y))) {
                    result[x][y] = 'E';
                } else {
                    result[x][y] = '.';
                }

                if (path[x][y] && result[x][y] == '.') {
                    result[x][y] = 'O';
                }
            }
        }
        return result;
    }

    public void printPath(boolean[][] path) {
        StringBuilder sb = new StringBuilder();
        char[][] data = buildString(path);
        for (int y = 0; y < walls[0].length; y++) {
            for (int x = 0; x < walls.length; x++) {
                sb.append(data[x][y]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < walls[0].length; y++) {
            for (int x = 0; x < walls.length; x++) {
                if (walls[x][y]) {
                    sb.append('#');
                } else if (reindeer.location().equals(new Point(x, y))) {
                    sb.append('S');
                } else if (finish.equals(new Point(x, y))) {
                    sb.append('E');
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
