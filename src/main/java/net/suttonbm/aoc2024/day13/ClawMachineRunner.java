package net.suttonbm.aoc2024.day13;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day13.model.ClawMachine;
import net.suttonbm.aoc2024.day13.service.ClawMachineReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClawMachineRunner implements CommandLineRunner {

    private final ClawMachineReader reader;

    @Override
    public void run(String... args) throws Exception {
        List<ClawMachine> input = reader.parse("13/input.txt");
        long totalCost = 0;
        for (ClawMachine machine : input) {
            totalCost += machine.prizeCost();
        }
        log.info("Total cost: " + totalCost);
    }
}
