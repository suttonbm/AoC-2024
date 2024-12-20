package net.suttonbm.aoc2024.day19.service;

import net.suttonbm.aoc2024.day19.model.TowelPuzzle;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TowelReader {
    public TowelPuzzle load(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);
        List<String> towels = parseTowels(data.get(0));
        data.remove(0);
        data.remove(0);

        return new TowelPuzzle(towels, data);
    }

    public List<String> parseTowels(String line) {
        return Arrays.stream(line.split(", ")).toList();
    }
}
