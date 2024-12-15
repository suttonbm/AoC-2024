package net.suttonbm.aoc2024.day14.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BathroomMap {
    private List<Robot> robots;
    private int mapWidth;
    private int mapHeight;
    private List<Long> safetyFactors = new ArrayList<>();

    public BathroomMap(List<Robot> robots, int mapWidth, int mapHeight) {
        this.robots = robots;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    public void doTicks(int numTicks) {
        for (int i = 0; i < numTicks; i++) {
            doTick();
            safetyFactors.add(getSafetyFactor());
        }
    }

    public void doTick() {
        for (Robot robot : robots) {
            doTick(robot);
        }
    }

    void doTick(Robot robot) {
        int newX = (robot.getPosition().x + robot.getVelocityX()) % mapWidth;
        int newY = (robot.getPosition().y + robot.getVelocityY()) % mapHeight;

        newX = newX < 0 ? newX + mapWidth : newX;
        newY = newY < 0 ? newY + mapHeight : newY;

        robot.setPosition(new Point(newX, newY));
    }

    public long getSafetyFactor() {
        int[] quadrantRobots = new int[4];
        for (Robot robot : robots) {
            int quadrant = getQuadrant(robot);
            if (quadrant != -1) {
                quadrantRobots[quadrant]++;
            }
        }
        return quadrantRobots[0] * quadrantRobots[1] * quadrantRobots[2] * quadrantRobots[3];
    }

    public int getQuadrant(Robot robot) {
        int halfX = mapWidth / 2;
        int halfY = mapHeight / 2;

        if (robot.getPosition().x < halfX
                && robot.getPosition().y < halfY) {
            return 0;
        } else if (robot.getPosition().x > halfX
                && robot.getPosition().y < halfY) {
            return 1;
        } else if (robot.getPosition().x < halfX
                && robot.getPosition().y > halfY) {
            return 2;
        } else if (robot.getPosition().x > halfX
                && robot.getPosition().y > halfY) {
            return 3;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        char[][] map = new char[mapWidth][mapHeight];
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[x][y] = '.';
            }
        }

        int[][] robotMap = new int[mapWidth][mapHeight];
        for (Robot robot : robots) {
            robotMap[robot.getPosition().x][robot.getPosition().y] += 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                if (robotMap[x][y] > 0) {
                    sb.append(robotMap[x][y]);
                } else {
                    sb.append(map[x][y]);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
