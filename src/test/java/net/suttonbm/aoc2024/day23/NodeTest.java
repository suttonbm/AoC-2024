package net.suttonbm.aoc2024.day23;

import net.suttonbm.aoc2024.day23.model.Node;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {
    @Test
    void testEquals() {
        Node n1 = new Node("A");
        Node n2 = new Node("A");
        assertThat(n1).isEqualTo(n2);
    }

    @Test
    void testNotEquals() {
        Node n1 = new Node("A");
        Node n2 = new Node("B");
        assertThat(n1).isNotEqualTo(n2);
    }

    @Test
    void testHashCode() {
        Node n1 = new Node("A");
        Node n2 = new Node("A");
        Set<Node> test = new HashSet<>();
        test.add(n1);
        assertThat(test.contains(n2)).isTrue();
    }
}