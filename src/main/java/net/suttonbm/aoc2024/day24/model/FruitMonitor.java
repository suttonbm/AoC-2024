package net.suttonbm.aoc2024.day24.model;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FruitMonitor {
    private Map<String, Wire> wires;
    private Set<Gate> gates;

    public Wire getWire(String name) {
        return wires.get(name);
    }

    public int numGates() {
        return gates.size();
    }

    public void run() {
        Set<Gate> pendingGates = new HashSet<>(gates);

        while (!pendingGates.isEmpty()) {
            Set<Gate> completedGates = new HashSet<>();
            for (Gate gate : pendingGates) {
                if (gate.isReady()) {
                    gate.run();
                    completedGates.add(gate);
                }
            }
            pendingGates.removeAll(completedGates);
        }
    }

    public long getOutput() {
        List<Wire> outputs = wires.values().stream()
                .filter(w -> w.getName().startsWith("z"))
                .sorted(Comparator.comparing(Wire::getName).reversed())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Wire w : outputs) {
            sb.append(w.val() ? "1" : "0");
        }
        return Long.parseLong(sb.toString(), 2);
    }
}