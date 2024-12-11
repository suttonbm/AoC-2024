package net.suttonbm.aoc2024.day11.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class Blinker {
    public List<Long> blink(List<Long> stones) {
        List<Long> result = new ArrayList<>();
        stones.forEach(stone -> {
            if (stone == 0) {
                result.add(1L);
            } else if (stone.toString().length() % 2 == 0) {
                String stoneStr = stone.toString();
                result.add(Long.parseLong(stoneStr.substring(0, stoneStr.length() / 2)));
                result.add(Long.parseLong(stoneStr.substring(stoneStr.length() / 2, stoneStr.length())));
            } else {
                result.add(stone * 2024);
            }
        });

        return result;
    }
}
