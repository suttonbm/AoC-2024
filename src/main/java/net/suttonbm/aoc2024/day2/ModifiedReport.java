package net.suttonbm.aoc2024.day2;

import lombok.Value;

import java.util.List;

@Value
class ModifiedReport {
    List<Integer> originalReport;
    int removedIndex;
    List<Integer> safeReport;
}