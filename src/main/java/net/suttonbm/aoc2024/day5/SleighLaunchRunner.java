package net.suttonbm.aoc2024.day5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day5.model.Constraint;
import net.suttonbm.aoc2024.day5.model.ProposedUpdate;
import net.suttonbm.aoc2024.day5.service.FileParserService;
import net.suttonbm.aoc2024.day5.service.ValidationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SleighLaunchRunner implements CommandLineRunner {

    private final FileParserService fileParserService;
    private final ValidationService validationService;

    @Override
    public void run(String... args) throws IOException {
        // Step 1: Parse the file to get constraints and proposed updates
        List<Object> parsedData = fileParserService.parseFile();
        List<Constraint> constraints = (List<Constraint>) parsedData.get(0);
        List<ProposedUpdate> proposedUpdates = (List<ProposedUpdate>) parsedData.get(1);

        // Step 2: Validate proposed updates based on constraints
        List<ProposedUpdate> validUpdates = validationService.validateProposedUpdates(constraints, proposedUpdates);
        log.debug("There are {} valid proposed updates", validUpdates.size());
        List<ProposedUpdate> invalidUpdates = new ArrayList<>(proposedUpdates);
        invalidUpdates.removeAll(validUpdates);  // Remaining updates are invalid
        log.debug("There are {} invalid proposed updates", invalidUpdates.size());

        // Step 3: Log the valid proposed updates
        log.debug("Valid ProposedUpdates:");
        validUpdates.forEach(update -> {
            String updateStr = String.join(",", update.getNumbers().stream().map(String::valueOf).toArray(String[]::new));
            log.debug(updateStr);
        });

        // Step 4: Calculate the sum of middle elements
        int sumOfMiddleElements = validationService.calculateSumOfMiddleElements(validUpdates);
        log.info("Sum of middle elements: {}", sumOfMiddleElements);

        // Step 5: Correct the invalid proposed updates
        List<ProposedUpdate> correctedUpdates = validationService.correctInvalidUpdates(constraints, invalidUpdates);

        // Step 6: Log the corrected proposed updates
        log.info("Corrected ProposedUpdates:");
        correctedUpdates.forEach(update -> {
            String updateStr = String.join(",", update.getNumbers().stream().map(String::valueOf).toArray(String[]::new));
            log.debug(updateStr);
        });

        // Step 7: Calculate the sum of middle elements for corrected updates
        int sumOfCorrectedMiddleElements = validationService.calculateSumOfMiddleElements(correctedUpdates);
        log.info("Sum of corrected middle elements: {}", sumOfCorrectedMiddleElements);
    }
}
