package net.suttonbm.aoc2024.day12;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day12.model.Garden;
import net.suttonbm.aoc2024.day12.service.GardenReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GardenRunner implements CommandLineRunner {

    private final GardenReader reader;

    @Override
    public void run(String... args) throws Exception {
        char[][] data = reader.readGarden("12/input.txt");
        Garden garden = new Garden(data);
        long result = garden.getFenceCost();
        log.info("Garden fence cost: {}", result);

        long discountResult = garden.getDiscountedCost();
        log.info("Garden discounted cost: {}", discountResult);
    }
}
