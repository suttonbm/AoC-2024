package net.suttonbm.aoc2024.day10;

import net.suttonbm.aoc2024.day10.model.TopoMap;
import net.suttonbm.aoc2024.day10.model.Trail;
import net.suttonbm.aoc2024.day10.model.TrailBuilder;
import net.suttonbm.aoc2024.day10.service.TopoMapParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TrailBuilderTest {

    private final TopoMapParser parser = new TopoMapParser();
    private TopoMap map;

    @BeforeEach
    void setUp() {
        map = parser.parseInputFile("10/simpleTest.txt");
    }

    @Test
    void scoreAtTopIsOne() {
        List<Point> summits = TrailBuilder.getTrailMap(map, new Point(6, 0));
        assertThat(summits.size()).isEqualTo(1);
    }

    @Test
    void scoreAdjacentToTopIsOne() {
        List<Point> summits = TrailBuilder.getTrailMap(map, new Point(5, 0));
        assertThat(summits.size()).isEqualTo(1);
    }

    @Test
    void scoreDirectPathToTopIsOne() {
        List<Point> summits = TrailBuilder.getTrailMap(map, new Point(3, 0));
        assertThat(summits.size()).isEqualTo(1);
    }

    @Test
    void scoreTwoPathsToTopIsTwo() {
        List<Point> summits = TrailBuilder.getTrailMap(map, new Point(3, 3));
        assertThat(summits.size()).isEqualTo(2);
    }

    @Test
    void scoreBottomToTopIsTwo() {
        List<Point> summits = TrailBuilder.getTrailMap(map, new Point(0, 3));
        assertThat(summits.size()).isEqualTo(2);
    }

    @Test
    public void simpleTest() {
        Trail trail = TrailBuilder.build(map, new Point(0, 3)).orElseThrow(() -> new AssertionError("Should have found a trail"));
        assertThat(trail.getScore()).isEqualTo(2);
    }
}
