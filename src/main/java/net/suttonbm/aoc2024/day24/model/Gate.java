package net.suttonbm.aoc2024.day24.model;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class Gate {
    private final Wire inputA;
    private final Wire inputB;
    private final Wire output;
    private final GateType gType;
    private final UUID uuid = UUID.randomUUID();

    public boolean isReady() {
        return inputA.hasFired() && inputB.hasFired() && !output.hasFired();
    }

    public boolean hasFired() {
        return output.hasFired();
    }

    public void run() {
        switch (gType) {
            case AND:
                output.fire(inputA.val() && inputB.val());
                break;
            case OR:
                output.fire(inputA.val() || inputB.val());
                break;
            case XOR:
                output.fire(inputA.val() ^ inputB.val());
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Gate gate = (Gate) o;
        return Objects.equals(uuid, gate.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}