package net.suttonbm.aoc2024.day6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class SimulationResult {
    private final int uniqueSpacesOccupied;
    private final Set<String> loopInducingPlacements;
}