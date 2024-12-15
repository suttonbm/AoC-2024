package net.suttonbm.aoc2024.day14;

import net.suttonbm.aoc2024.day14.model.Robot;
import net.suttonbm.aoc2024.day14.service.BathroomParser;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BathroomParserTest {
    private final BathroomParser parser = new BathroomParser();

    @Test
    public void testParseLine() {
        String test = "p=0,4 v=3,-3";
        Robot testRobot = parser.readLine(test);

        assertThat(testRobot.getPosition()).isEqualTo(new Point(0, 4));
        assertThat(testRobot.getVelocityX()).isEqualTo(3);
        assertThat(testRobot.getVelocityY()).isEqualTo(-3);
    }

    @Test
    public void testParseFile() {
        String testFile = "14/test.txt";
        List<Robot> testData = parser.load(testFile);

        assertThat(testData).hasSize(12);
    }
}
