package net.suttonbm.aoc2024.day19;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day19.model.TowelPuzzle;
import net.suttonbm.aoc2024.day19.service.TowelReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TowelMatchRunner implements CommandLineRunner {

    private final TowelReader reader;

    @Override
    public void run(String... args) throws Exception {
        TowelPuzzle puzzle = reader.load("19/input.txt");
        long result = puzzle.solve();
        log.info("There are {} solutions to patterns.", result);
    }
}
