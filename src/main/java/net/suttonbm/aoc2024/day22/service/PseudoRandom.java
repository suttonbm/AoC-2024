package net.suttonbm.aoc2024.day22.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PseudoRandom {
    public List<Long> get(int initial, int iteration) {
        Long result = Long.valueOf(initial);
        List<Long> results = new ArrayList<>();
        results.add(result);
        for (int i = 0; i < iteration; i++) {
            result = get(result);
            results.add(result);
        }
        return results;
    }

    public long get(long initial) {
        long result = initial * 64;
        result = prune(mix(initial, result));
        result = prune(mix(result / 32, result));
        result = prune(mix(result * 2048, result));
        return result;
    }

    private long mix(long a, long b) {
        return a ^ b;
    }

    private long prune(long a) {
        return a % 16777216;
    }
}