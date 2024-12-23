package net.suttonbm.aoc2024.day23;

import net.suttonbm.aoc2024.day23.model.Edge;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class EdgeTest {
    @Test
    void testEquals() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "B");
        assertThat(e1).isEqualTo(e2);
    }

    @Test
    void testEqualsFlipped() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("B", "A");
        assertThat(e1).isEqualTo(e2);
    }

    @Test
    void testHashcodeContains() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("A", "B");
        Set<Edge> test = new HashSet<>();
        test.add(e1);
        assertThat(test.contains(e2)).isTrue();
    }

    @Test
    void testHashcodeContainsFlipped() {
        Edge e1 = new Edge("A", "B");
        Edge e2 = new Edge("B", "A");
        Set<Edge> test = new HashSet<>();
        test.add(e1);
        assertThat(test.contains(e2)).isTrue();
    }
}