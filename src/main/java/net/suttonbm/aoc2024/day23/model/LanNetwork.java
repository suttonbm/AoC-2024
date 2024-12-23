package net.suttonbm.aoc2024.day23.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LanNetwork {
    @Getter
    private Set<Edge> edges;
    @Getter
    private Map<String, Node> nodes;

    public LanNetwork() {
        edges = new HashSet<>();
        nodes = new HashMap<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);

        Node n1, n2;
        if (nodes.containsKey(e.a())) {
            n1 = nodes.get(e.a());
        } else {
            n1 = new Node(e.a());
        }
        if (nodes.containsKey(e.b())) {
            n2 = nodes.get(e.b());
        } else {
            n2 = new Node(e.b());
        }

        n1.add(n2);
        n2.add(n1);

        nodes.put(e.a(), n1);
        nodes.put(e.b(), n2);
    }

    public Node getNode(String id) {
        return nodes.get(id);
    }
}