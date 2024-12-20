package net.suttonbm.aoc2024.day20;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day20.model.RaceMap;
import net.suttonbm.aoc2024.day20.service.MapReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CheatyProgramRunner implements CommandLineRunner {

    private final MapReader reader;

    @Override
    public void run(String... args) throws Exception {
        RaceMap map = reader.load("20/input.txt");
        List<Integer> cheats = map.getCheats();
        long result = cheats.stream().filter((cheat) -> cheat >= 100).count();
        log.info("There are {} cheats that save 100ps", result);
        List<Integer> betterCheats = map.getBetterCheats();
        result = betterCheats.stream().filter((cheat) -> cheat >= 100).count();
        log.info("There are {} better cheats that save 100ps", result);
    }
}
