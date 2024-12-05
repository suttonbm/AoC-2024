package net.suttonbm.aoc2024.day4.model;

import lombok.Value;
import net.suttonbm.aoc2024.day4.strategy.SearchDirection;
import net.suttonbm.aoc2024.day4.strategy.SubmatrixTransform;

@Value
public class Match {
    int row;
    int col;
    SearchDirection direction;
    SubmatrixTransform transform;
}
