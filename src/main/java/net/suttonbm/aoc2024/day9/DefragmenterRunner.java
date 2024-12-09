package net.suttonbm.aoc2024.day9;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day9.model.File;
import net.suttonbm.aoc2024.day9.service.BetterDefragmenter;
import net.suttonbm.aoc2024.day9.service.Checksum;
import net.suttonbm.aoc2024.day9.service.Defragmenter;
import net.suttonbm.aoc2024.day9.service.DiskFileParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefragmenterRunner implements CommandLineRunner {
    private final DiskFileParser inputFileParser;
    private final Defragmenter defragmenter;
    private final BetterDefragmenter betterDefragmenter;
    private final Checksum checksum;

    @Override
    public void run(String... args) {
        String inputFilePath = "9/input.txt";
        List<File> input = inputFileParser.parseInputFile(inputFilePath);
        List<File> output = defragmenter.run(input);
        long result = checksum.calculate(output);
        log.info("Checksum after defrag: {}", result);

        List<File> newOutput = betterDefragmenter.run(input);
        long newResult = checksum.calculate(newOutput);
        log.info("Checksum after better defrag: {}", newResult);
    }
}
