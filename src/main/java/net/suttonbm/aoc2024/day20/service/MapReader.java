package net.suttonbm.aoc2024.day20.service;

import net.suttonbm.aoc2024.day20.model.RaceMap;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.awt.Point;

@Service
public class MapReader {
    public RaceMap load(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);
        boolean[][] walls = new boolean[data.size()][data.get(0).length()];
        Point start = new Point(-1, -1);
        Point end = new Point(-1, -1);

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length(); j++) {
                char c = data.get(i).charAt(j);
                switch (c) {
                    case '#':
                        walls[i][j] = true;
                        break;
                    case 'S':
                        start = new Point(i, j);
                        break;
                    case 'E':
                        end = new Point(i, j);
                        break;
                }
            }
        }

        return new RaceMap(walls, start, end);
    }
}
