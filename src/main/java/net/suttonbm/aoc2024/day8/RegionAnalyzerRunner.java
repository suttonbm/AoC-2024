package net.suttonbm.aoc2024.day8;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day8.service.AntinodesCalculator;
import net.suttonbm.aoc2024.day8.service.RegionFileParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegionAnalyzerRunner implements CommandLineRunner {

    private final AntinodesCalculator antinodesCalculator;
    private final RegionFileParser inputFileParser;

    @Override
    public void run(String... args) {
        String inputFilePath = "8/input.txt";
        char[][] region = inputFileParser.parseInputFile(inputFilePath);
        int uniqueAntinodes = antinodesCalculator.calculateUniqueAntinodes(region);
        log.info("Unique Antinodes: {}", uniqueAntinodes);
    }
}