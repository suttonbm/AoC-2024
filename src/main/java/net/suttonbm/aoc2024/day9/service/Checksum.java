package net.suttonbm.aoc2024.day9.service;

import net.suttonbm.aoc2024.day9.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Checksum {
    public long calculate(List<File> input) {
        long result = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).getId() != null) {
                result += i * input.get(i).getId();
            }
        }
        return result;
    }
}
