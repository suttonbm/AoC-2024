package net.suttonbm.aoc2024.day18.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

@Service
@Slf4j
public class MemoryReader {
    public List<Point> load(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);
        List<Point> points = new ArrayList<>();

        for (String line : data) {
            String[] split = line.split(",");
            points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        return points;
    }
}
