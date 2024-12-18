package net.suttonbm.aoc2024.day14.service;

import net.suttonbm.aoc2024.day14.model.Robot;
import net.suttonbm.aoc2024.day4.exception.InvalidInputException;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BathroomParser {
    public Robot readLine(String input) {
        Pattern pattern = Pattern.compile("p=(-?\\d+),(-?\\d+)\\s+v=(-?\\d+),(-?\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int p1 = Integer.parseInt(matcher.group(1));
            int p2 = Integer.parseInt(matcher.group(2));
            int v1 = Integer.parseInt(matcher.group(3));
            int v2 = Integer.parseInt(matcher.group(4));

            return new Robot(new Point(p1, p2), v1, v2);
        } else {
            throw new InvalidInputException("Invalid robot format found.");
        }
    }

    public List<Robot> load(String inputFile) {
        List<String> lines = ResourceReaderService.get(inputFile);
        List<Robot> robots = new ArrayList<>();
        for (String line : lines) {
            robots.add(readLine(line));
        }
        return robots;
    }
}
