package net.suttonbm.aoc2024.day17.service;

import net.suttonbm.aoc2024.day17.model.Phone;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhoneFixerService {

    private Map<Long, Long> lastIteration;

    private record Range(Long low, Long high) {}

    public long fixPhone(Phone broken) {
        Range testRange = new Range(0L, Long.MAX_VALUE);
        Map<Long, Long> scores = new HashMap<>();

        while (testRange.low < testRange.high) {
            testRange = getBestScores(testRange, broken);
        }

        return testRange.low;
    }

    private Range getBestScores(Range testRange, Phone broken) {
        long numSteps = 1000;
        long maxScore = 1 << broken.getProgram().size();
        if (testRange.high - testRange.low < 10000) {
            numSteps = testRange.high - testRange.low;
        }
        long step = (testRange.high - testRange.low) / numSteps;
        Map<Long, Long> scores = new HashMap<>();
        for (int i = 0; i <= numSteps; i++) {
            long testVal = testRange.low + step * i;
            long testScore = score(test(broken, testVal), broken);
            scores.put(testVal, testScore);
        }

        System.out.println(scores);

        long bestScore = 0;
        for (Map.Entry<Long, Long> entry : scores.entrySet()) {
            if (entry.getValue() > bestScore) {
                bestScore = entry.getValue();
            }
        }

        if (bestScore == 0L) {
            return new Range(testRange.low, testRange.low + step);
        }

        long minVal = Long.MAX_VALUE;
        long maxVal = 0L;
        for (Map.Entry<Long, Long> entry : scores.entrySet()) {
            if (entry.getValue() == bestScore && entry.getKey() <= minVal) {
                minVal = entry.getKey();
            }
            if (entry.getValue() == bestScore && entry.getKey() >= maxVal) {
                maxVal = entry.getKey();
            }
        }

        if (bestScore == maxScore && step == 1) {
            lastIteration = scores;
            return new Range(minVal, minVal);
        }

        if (scores.equals(lastIteration)) {
            lastIteration = scores;
            return new Range(testRange.low + step/2, testRange.high - step/2);
        }

        if (minVal == maxVal) {
            lastIteration = scores;
            return new Range(minVal - step, minVal + step);
        }

        lastIteration = scores;
        return new Range(minVal, maxVal);
    }

    private List<Integer> test(Phone broken, long val) {
        Phone phone = new Phone(val, 0, 0);
        phone.setProgram(broken.getProgram());
        return phone.run();
    }

    private long score(List<Integer> values, Phone phone) {
        if (values.size() != phone.getProgram().size()) {
            return 0;
        }

        long score = 1;
        for (int i = 0; i < values.size(); i++) {
            long rankVal = 1L << i;
            score += values.get(i) == phone.getProgram().get(i) ? rankVal : 0;
        }

        return score;
    }
}
