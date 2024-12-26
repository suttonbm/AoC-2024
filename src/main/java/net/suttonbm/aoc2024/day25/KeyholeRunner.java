package net.suttonbm.aoc2024.day25;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day25.model.KeyData;
import net.suttonbm.aoc2024.day25.service.KeyholeReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeyholeRunner implements CommandLineRunner {

    private final KeyholeReader reader;

    @Override
    public void run(String... args) throws Exception {
        KeyData data = reader.read("25/input.txt");
        long result = data.possibleMatches();
        log.info("Possible matches: {}", result);
    }
}