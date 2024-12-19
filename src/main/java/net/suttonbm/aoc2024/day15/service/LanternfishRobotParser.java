package net.suttonbm.aoc2024.day15.service;

import net.suttonbm.aoc2024.day15.model.BiggerLanternfishWarehouse;
import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.awt.Point;
import java.util.List;

@Service
public class LanternfishRobotParser {
    public LanternfishWarehouse load(String inputFile) {
        List<String> lines = ResourceReaderService.get(inputFile);

        int rows = getMapRows(lines);
        int cols = getMapCols(lines);
        boolean[][] walls = new boolean[cols][rows];
        boolean[][] boxes = new boolean[cols][rows];
        Point robot = new Point(-1,-1);
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                char c = lines.get(y).charAt(x);
                if (c == '#') {
                    walls[x][y] = true;
                } else if (c == 'O') {
                    boxes[x][y] = true;
                } else if (c == '@') {
                    robot = new Point(x, y);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = rows + 1; i < lines.size(); i++) {
            sb.append(lines.get(i));
        }
        char[] robotOps = sb.toString().toCharArray();

        return new LanternfishWarehouse(walls, boxes, robot, robotOps);
    }

    public BiggerLanternfishWarehouse loadBigger(String inputFile) {
        List<String> lines = ResourceReaderService.get(inputFile);

        int rows = getMapRows(lines);
        int cols = getMapCols(lines);
        boolean[][] walls = new boolean[cols][rows];
        boolean[][] boxes = new boolean[cols][rows];
        Point robot = new Point(-1,-1);
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                char c = lines.get(y).charAt(x);
                if (c == '#') {
                    walls[x][y] = true;
                } else if (c == 'O') {
                    boxes[x][y] = true;
                } else if (c == '@') {
                    robot = new Point(x, y);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = rows + 1; i < lines.size(); i++) {
            sb.append(lines.get(i));
        }
        char[] robotOps = sb.toString().toCharArray();

        return new BiggerLanternfishWarehouse(walls, boxes, robot, robotOps);
    }

    private int getMapRows(List<String> lines) {
        int curLine = 1;
        while (!lines.get(curLine).startsWith("#####")) {
            curLine++;
        }
        return curLine + 1;
    }

    private int getMapCols(List<String> lines) {
        String firstLine = lines.get(1);
        return firstLine.length();
    }
}
