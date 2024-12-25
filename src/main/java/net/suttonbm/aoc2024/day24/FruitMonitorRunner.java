package net.suttonbm.aoc2024.day24;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day24.model.FruitMonitor;
import net.suttonbm.aoc2024.day24.service.FruitMonitorReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FruitMonitorRunner implements CommandLineRunner {

    private final FruitMonitorReader reader;

    @Override
    public void run(String... args) throws Exception {
        FruitMonitor monitor = reader.load("24/input.txt");
        monitor.run();
        long result = monitor.getOutput();

        log.info("FruitMonitor output: {}", result);
    }
}