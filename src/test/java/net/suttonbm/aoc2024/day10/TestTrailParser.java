package net.suttonbm.aoc2024.day10;

import net.suttonbm.aoc2024.day10.model.TopoMap;
import net.suttonbm.aoc2024.day10.service.TopoMapParser;
import org.junit.jupiter.api.Test;

public class TestTrailParser {

    private final TopoMapParser parser = new TopoMapParser();

    @Test
    public void test() {
        String filePath = "10/test.txt";
        TopoMap result = parser.parseInputFile(filePath);
        System.out.println(result);
    }
}
