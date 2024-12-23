package net.suttonbm.aoc2024.day22;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day22.service.MonkeySequence;
import net.suttonbm.aoc2024.day22.service.PseudoRandom;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class BazaarRunner implements CommandLineRunner {

    private final PseudoRandom pseudoRandom;
    private final MonkeySequence monkeySequence;

    @Override
    public void run(String... args) throws Exception {
        List<Integer> numbers = ResourceReaderService.get("22/input.txt").stream()
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        List<List<Long>> prValues = numbers.stream()
                .map((i) -> pseudoRandom.get(i, 2000))
                .collect(Collectors.toList());

        List<Long> results = prValues.stream().map((l) -> l.get(2000))
                .collect(Collectors.toList());
        long result = results.stream().reduce(0L, Long::sum);
        log.info("Sum of PR results: {}", result);

        long bananas = monkeySequence.solve(prValues);
        log.info("Monkey got {} bananas", bananas);
    }
}