package net.suttonbm.aoc2024.day23.model;

import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class Node {
    private String name;
    private Set<Edge> connections;

    public Node(String name) {
        this.name = name;
        connections = new HashSet<>();
    }

    public void add(Node n2) {
        connections.add(new Edge(this.name, n2.name));
    }

    public boolean contains(Node n2) {
        return connections.contains(new Edge(this.name, n2.name));
    }

    public boolean contains(String n2) {
        return connections.contains(new Edge(this.name, n2));
    }

    public boolean containsAll(Set<String> nodes) {
        boolean result = true;
        for (String n : nodes) {
            if (!contains(n)) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}