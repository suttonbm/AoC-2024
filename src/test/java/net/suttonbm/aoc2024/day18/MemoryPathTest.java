package net.suttonbm.aoc2024.day18;

import net.suttonbm.aoc2024.day18.model.MemoryPathSearch;
import net.suttonbm.aoc2024.day18.service.MemoryReader;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryPathTest {

    private final MemoryReader reader = new MemoryReader();

    @Test
    void testExample() {
        List<Point> data = reader.load("18/test.txt");
        MemoryPathSearch mps = new MemoryPathSearch(data, 7, new Point(0,0), new Point(6,6));
        int result = mps.getBestPathAt(12);
        assertThat(result).isEqualTo(22);
        Point badPoint = mps.getBlockingPoint(0);
        System.out.println(badPoint);
        assertThat(badPoint.x).isEqualTo(6);
        assertThat(badPoint.y).isEqualTo(1);
    }
}
