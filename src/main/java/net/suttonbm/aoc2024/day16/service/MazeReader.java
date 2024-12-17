package net.suttonbm.aoc2024.day16.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day16.model.Direction;
import net.suttonbm.aoc2024.day16.model.Maze;
import net.suttonbm.aoc2024.day16.model.Reindeer;
import net.suttonbm.aoc2024.utils.ResourceReader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.awt.Point;

@Service
@Slf4j
public class MazeReader {
    public Maze load(String inputFile) throws Exception {
        List<String> lines = ResourceReader.get(inputFile);

        return parse(lines);
    }

    public Maze parse(List<String> lines)  throws Exception {
        int rows = lines.size();
        int cols = lines.get(0).length();

        boolean walls[][] = new boolean[cols][rows];
        Point start = null;
        Point finish = null;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = lines.get(i).charAt(j);
                switch (c) {
                    case '#':
                        walls[j][i] = true;
                        break;
                    case 'S':
                        start = new Point(j, i);
                        break;
                    case 'E':
                        finish = new Point(j, i);
                        break;
                }
            }
        }

        if (start == null || finish == null) {
            throw new Exception("No start or finish was found");
        }

        Reindeer deer = new Reindeer(start, Direction.EAST);
        Maze maze = new Maze(walls, deer, finish);

        return maze;
    }
}
