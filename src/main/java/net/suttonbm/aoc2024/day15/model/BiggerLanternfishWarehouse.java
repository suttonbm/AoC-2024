package net.suttonbm.aoc2024.day15.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class BiggerLanternfishWarehouse {
    private boolean[][] walls;
    private Map<Point, Box> boxes;
    private Point robot;
    private char[] robotOps;

    private record Box(Point l, Point r) {}

    public BiggerLanternfishWarehouse(boolean[][] walls, boolean[][] boxes, Point robot, char[] robotOps) {
        this.robotOps = robotOps.clone();
        this.robot = new Point(robot.x*2, robot.y);
        initWalls(walls);
        initBoxes(boxes);
    }

    private void initWalls(boolean[][] walls) {
        this.walls = new boolean[walls.length*2][walls[0].length];
        for (int x = 0; x < walls.length; x++) {
            for (int y = 0; y < walls[0].length; y++) {
                this.walls[2*x][y] = walls[x][y];
                this.walls[2*x+1][y] = walls[x][y];
            }
        }
    }

    private void initBoxes(boolean[][] boxes) {
        this.boxes = new HashMap<>();
        for (int x = 0; x < boxes.length; x++) {
            for (int y = 0; y < boxes[0].length; y++) {
                if (!boxes[x][y]) {
                    continue;
                }
                Point l = new Point(2*x, y);
                Point r = new Point(2*x+1, y);
                Box box = new Box(l, r);

                this.boxes.put(l, box);
                this.boxes.put(r, box);
            }
        }
    }

    public void run() {
        for (char c : robotOps) {
            doOp(c);
        }
    }

    public void doOp(char c) {
        switch (c) {
            case '<':
                doOp('x', -1);
                break;
            case '>':
                doOp('x', 1);
                break;
            case '^':
                doOp('y', -1);
                break;
            case 'v':
                doOp('y', 1);
                break;
        }
    }

    private void doOp(char direction, int sign) {
        int x = direction == 'x' ? sign : 0;
        int y = direction == 'y' ? sign : 0;

        if (!canPush(robot.x, robot.y, x, y)) {
            // ABORT!
            return;
        }

        // Update Robot
        robot = new Point(robot.x + x, robot.y + y);

        if (boxes.containsKey(robot)) {
            updateBoxes(robot, x, y);
        }
    }

    private void updateBoxes(Point p, int x, int y) {
        Box box = boxes.remove(p);
        if (boxes.containsKey(box.l)) {
            boxes.remove(box.l);
        } else {
            boxes.remove(box.r);
        }

        Point newL = new Point(box.l.x+x, box.l.y+y);
        Point newR = new Point(box.r.x+x, box.r.y+y);
        Box newBox = new Box(newL, newR);

        if (boxes.containsKey(newL)) {
            updateBoxes(newL, x, y);
        }

        if (boxes.containsKey(newR)) {
            updateBoxes(newR, x, y);
        }

        boxes.put(newL, newBox);
        boxes.put(newR, newBox);
    }

    private boolean canPush(int xLoc, int yLoc, int xDelta, int yDelta) {
        Point newLoc = new Point(xLoc + xDelta, yLoc + yDelta);

        if (walls[newLoc.x][newLoc.y]) {
            return false;
        } else if (!boxes.containsKey(newLoc)) {
            return true;
        } else {
            // There is a box
            Box box = boxes.get(newLoc);
            if (xDelta != 0) {
                // X direction is same as before, but direction affects which next location to check.
                if (box.l.x == newLoc.x) {
                    return canPush(box.r.x, box.r.y, xDelta, yDelta);
                } else {
                    return canPush(box.l.x, box.l.y, xDelta, yDelta);
                }
            } else {
                // Y direction must check both segments.
                return canPush(box.r.x, box.r.y, xDelta, yDelta)
                        && canPush(box.l.x, box.l.y, xDelta, yDelta);
            }
        }
    }

    public List<Integer> getCoordinates() {
        List<Integer> result = new ArrayList<>();
        for (Box box : boxes.values()) {
            result.add(box.l.x + 100*box.l.y);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < walls[0].length; y++) {
            for (int x = 0; x < walls.length; x++) {
                if (walls[x][y]) {
                    sb.append('#');
                } else if (boxes.containsKey(new Point(x, y))) {
                    Box box = boxes.get(new Point(x, y));
                    if (box.l.x == x && box.l.y == y) {
                        sb.append('[');
                    } else {
                        sb.append(']');
                    }
                } else if (new Point(x, y).equals(robot)) {
                    sb.append('@');
                } else {
                    sb.append('.');
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
