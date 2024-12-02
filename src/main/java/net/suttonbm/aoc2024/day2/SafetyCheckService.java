package net.suttonbm.aoc2024.day2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SafetyCheckService {

    public boolean isSafeReport(List<Integer> report) {
        boolean isIncreasing = isConsistentlyIncreasing(report);
        boolean isDecreasing = isConsistentlyDecreasing(report);

        if (isIncreasing || isDecreasing) {
            return isAdjacentLevelsDifferenceWithinRange(report);
        }

        return false;
    }

    public SingleRemovalResult isSingleRemovalSafe(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafeReport(modifiedReport)) {
                return new SingleRemovalResult(true, i, modifiedReport);
            }
        }
        return new SingleRemovalResult(false, -1, null);
    }

    private boolean isConsistentlyIncreasing(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) <= report.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isConsistentlyDecreasing(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) >= report.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAdjacentLevelsDifferenceWithinRange(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            int diff = Math.abs(report.get(i) - report.get(i - 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }
}