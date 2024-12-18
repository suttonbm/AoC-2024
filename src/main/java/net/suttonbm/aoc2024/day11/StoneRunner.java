package net.suttonbm.aoc2024.day11;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day11.service.BetterBlinker;
import net.suttonbm.aoc2024.day11.service.Blinker;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class StoneRunner implements CommandLineRunner {

    private final Blinker blinker;
    private final BetterBlinker betterBlinker;

    @Override
    public void run(String... args) throws Exception {
        List<String> input = ResourceReaderService.get("11/input.txt");
        List<Long> stones = Arrays.stream(input.get(0).split("\\s"))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());

        List<Long> moreStones = new ArrayList<>(stones);

        for (int i = 0; i < 25; i++) {
            stones = blinker.blink(stones);
        }

        log.info("There are {} stones after 25 blinks.", stones.size());

        long numStones = betterBlinker.blink(moreStones, 75);

        log.info("There are {} stones after 75 blinks.", numStones);
    }
}
