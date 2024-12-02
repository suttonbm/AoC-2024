package net.suttonbm.aoc2024.day2;

import lombok.Value;

import java.util.List;

@Value
class SingleRemovalResult {
    boolean safe;
    int removedIndex;
    List<Integer> safeReport;
}