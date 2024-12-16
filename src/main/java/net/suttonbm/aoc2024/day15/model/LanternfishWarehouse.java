package net.suttonbm.aoc2024.day15.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class LanternfishWarehouse {
    private boolean[][] walls;
    private boolean[][] boxes;
    private Point robot;
    private char[] robotOps;

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

        // Update Boxes
        int checkX = robot.x;
        int checkY = robot.y;
        boolean[][] newBoxes = deepCopy(boxes);
        newBoxes[checkX][checkY] = false;
        while (boxes[checkX][checkY]) {
            newBoxes[checkX+x][checkY+y] = true;
            checkX += x;
            checkY += y;
        }
        boxes = newBoxes;
    }

    private boolean canPush(int xLoc, int yLoc, int xDelta, int yDelta) {
        if (walls[xLoc + xDelta][yLoc + yDelta]) {
            return false;
        } else if (!boxes[xLoc + xDelta][yLoc + yDelta]) {
            return true;
        } else {
            return canPush(xLoc + xDelta, yLoc + yDelta, xDelta, yDelta);
        }
    }

    public List<Integer> getCoordinates() {
        List<Integer> result = new ArrayList<>();
        for (int x = 0; x < walls.length; x++) {
            for (int y = 0; y < walls[0].length; y++) {
                if (boxes[x][y]) {
                    result.add(x + 100*y);
                }
            }
        }
        return result;
    }

    private boolean[][] deepCopy(boolean[][] boxes) {
        boolean[][] newBoxes = new boolean[boxes.length][boxes[0].length];
        for (int i = 0; i < boxes.length; i++) {
            newBoxes[i] = boxes[i].clone();
        }
        return newBoxes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < walls[0].length; y++) {
            for (int x = 0; x < walls.length; x++) {
                if (walls[x][y]) {
                    sb.append('#');
                } else if (boxes[x][y]) {
                    sb.append('O');
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
