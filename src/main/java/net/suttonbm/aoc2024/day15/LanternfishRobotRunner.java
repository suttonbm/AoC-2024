package net.suttonbm.aoc2024.day15;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day15.model.BiggerLanternfishWarehouse;
import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import net.suttonbm.aoc2024.day15.service.LanternfishRobotParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LanternfishRobotRunner implements CommandLineRunner {

    private final LanternfishRobotParser parser;

    public void run(String... args) throws Exception {
        LanternfishWarehouse wh = parser.load("15/input.txt");
        wh.run();
        long result = wh.getCoordinates().stream().mapToLong(Long::valueOf).sum();
        log.info("Lanternfish robot result: {}", result);

        BiggerLanternfishWarehouse whb = parser.loadBigger("15/input.txt");
        whb.run();
        result = whb.getCoordinates().stream().mapToLong(Long::valueOf).sum();
        log.info("Bigger lanternfish robot result: {}", result/2);
    }
}
