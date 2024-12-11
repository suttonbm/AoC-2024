package net.suttonbm.aoc2024.day10;

import net.suttonbm.aoc2024.day10.model.TopoMap;
import net.suttonbm.aoc2024.day10.model.Trail;
import net.suttonbm.aoc2024.day10.service.TopoMapParser;
import net.suttonbm.aoc2024.day10.service.TrailFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TrailFinderTest {

    private final TopoMapParser parser = new TopoMapParser();
    private final TrailFinder finder = new TrailFinder();
    private TopoMap map;

    @BeforeEach
    void setUp() {
        map = parser.parseInputFile("10/test.txt");
    }

    @Test
    void testScoresExample() {
        List<Trail> trails = finder.search(map);

        assertThat(trails.size()).isEqualTo(9);

        List<Integer> scores = trails.stream()
                .map(Trail::getScore)
                .collect(Collectors.toList());

        List<Integer> expected = List.of(5, 6, 5, 3, 1, 3, 5, 3, 5);

        assertThat(scores).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void testRatingsExample() {
        List<Trail> trails = finder.search(map);

        assertThat(trails.size()).isEqualTo(9);

        List<Integer> ratings = trails.stream()
                .map(Trail::getRating)
                .collect(Collectors.toList());

        List<Integer> expected = List.of(20,24,10,4,1,4,5,8,5);

        assertThat(ratings).containsExactlyInAnyOrderElementsOf(expected);
    }
}
