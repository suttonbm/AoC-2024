package net.suttonbm.aoc2024.day11.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BetterBlinker {

    private Map<Long, Long> uniqueStones = new HashMap<>();

    public long blink(List<Long> stones, int times) {
        for (Long stone : stones) {
            addStone(stone, 1);
        }

        blinkStones(times);

        long result = 0;
        for (Long count : uniqueStones.values()) {
            result += count;
        }
        return result;
    }

    private void addStone(Long stone, long times) {
        if (uniqueStones.containsKey(stone)) {
            uniqueStones.put(stone, uniqueStones.get(stone) + times);
        } else {
            uniqueStones.put(stone, times);
        }
    }

    private void blinkStones(int times) {
        if (times == 0) {
            return;
        }

        Map<Long, Long> oldStones = new HashMap<>(uniqueStones);
        uniqueStones.clear();

        for (Long stone : oldStones.keySet()) {
            if (stone == 0L) {
                addStone(1L, oldStones.get(stone));
            } else if (stone.toString().length() % 2 == 0) {
                String stoneStr = stone.toString();
                Long leftStone = Long.parseLong(stoneStr.substring(0, stoneStr.length() / 2));
                Long rightStone = Long.parseLong(stoneStr.substring(stoneStr.length() / 2, stoneStr.length()));

                addStone(leftStone, oldStones.get(stone));
                addStone(rightStone, oldStones.get(stone));
            } else {
                Long newStone = stone * 2024;
                addStone(newStone, oldStones.get(stone));
            }
        }

        blinkStones(times - 1);
    }
}
