package net.suttonbm.aoc2024.day14;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day14.model.BathroomMap;
import net.suttonbm.aoc2024.day14.model.Robot;
import net.suttonbm.aoc2024.day14.service.BathroomParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class BathroomRunner implements CommandLineRunner {

    private final BathroomParser parser;

    @Override
    public void run(String... args) throws Exception {
        List<Robot> robots = parser.load("14/input.txt");
        BathroomMap map = new BathroomMap(robots, 101, 103);
        map.doTicks(100);
        long result = map.getSafetyFactor();

        log.info("Safety factor after 100s: {}", result);

        for (int i=100; i<=10000; i++) {
            map.doTick();
            if (i == 5830 || i == 6492) {
                log.debug(map.toString());
            }
        }
        Long maxAvg = map.getSafetyFactors().stream().max(Long::compareTo).get();
        int maxAvgIdx = map.getSafetyFactors().indexOf(maxAvg);
        Long minAvg = map.getSafetyFactors().stream().min(Long::compareTo).get();
        int minAvgIdx = map.getSafetyFactors().indexOf(minAvg);

        log.info("Max avg safety factor ({}) occurs at iteration {}.", maxAvg, maxAvgIdx);
        log.info("Min avg safety factor ({}) occurs at iteration {}.", minAvg, minAvgIdx);
    }
}
