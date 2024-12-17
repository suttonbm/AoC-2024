package net.suttonbm.aoc2024.day17;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day17.model.Phone;
import net.suttonbm.aoc2024.day17.service.PhoneFixerService;
import net.suttonbm.aoc2024.day17.service.PhoneParserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class HistorianPhoneRunner implements CommandLineRunner {

    private final PhoneParserService parser;
    private final PhoneFixerService fixerService;

    @Override
    public void run(String... args) throws Exception {
        Phone phone = parser.initialize("17/input.txt");
        List<Integer> output = phone.run();
        log.info("Program output: {}", output.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")));
        log.info("Fixed register value: {}", fixerService.fixPhone(phone));
    }
}
