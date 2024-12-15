package net.suttonbm.aoc2024.day14;

import net.suttonbm.aoc2024.day14.model.BathroomMap;
import net.suttonbm.aoc2024.day14.model.Robot;
import net.suttonbm.aoc2024.day14.service.BathroomParser;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BathroomMapTest {

    private final BathroomParser parser = new BathroomParser();

    @Test
    void testTick() {
        BathroomMap map = new BathroomMap(
                List.of(new Robot(new Point(0,0), 1, 1)),
                10, 10
        );

        map.doTicks(1);

        assertThat(map.getRobots().get(0).getPosition()).isEqualTo(new Point(1, 1));
    }

    @Test
    void testTickWrap() {
        BathroomMap map = new BathroomMap(
                List.of(new Robot(new Point(0,0), 1, 1)),
                10, 10
        );

        map.doTicks(10);

        assertThat(map.getRobots().get(0).getPosition()).isEqualTo(new Point(0, 0));
    }

    @Test
    void testQuadrant() {
        BathroomMap map = new BathroomMap(
                List.of(new Robot(new Point(0,0), 1, 1)),
                11, 11
        );

        Point q1 = new Point(4, 4);
        Point q2 = new Point(6, 4);
        Point q3 = new Point(4, 6);
        Point q4 = new Point(6, 6);
        Point ignored = new Point(5, 5);

        assertThat(map.getQuadrant(new Robot(q1, 1, 1))).isEqualTo(0);
        assertThat(map.getQuadrant(new Robot(q2, 1, 1))).isEqualTo(1);
        assertThat(map.getQuadrant(new Robot(q3, 1, 1))).isEqualTo(2);
        assertThat(map.getQuadrant(new Robot(q4, 1, 1))).isEqualTo(3);
        assertThat(map.getQuadrant(new Robot(ignored, 1, 1))).isEqualTo(-1);
    }

    @Test
    void exampleTest() {
        List<Robot> robots = parser.load("14/test.txt");
        BathroomMap map = new BathroomMap(robots, 11, 7);

        assertThat(map.getRobots()).hasSize(12);
        System.out.println(map);

        map.doTicks(100);
        System.out.println(map);
        long result = map.getSafetyFactor();

        assertThat(result).isEqualTo(12);
    }
}
