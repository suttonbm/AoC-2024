package net.suttonbm.aoc2024.day6;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day6.model.Courtyard;
import net.suttonbm.aoc2024.day6.model.SimulationResult;
import net.suttonbm.aoc2024.day6.service.CourtyardParserService;
import net.suttonbm.aoc2024.day6.service.PatrolSimulator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
public class GuardPatrolRunner implements CommandLineRunner {

    private final CourtyardParserService courtyardParser;
    private final PatrolSimulator patrolSimulator;

    @Override
    public void run(String... args) {
        String inputFilePath = "/6/input.txt";

        try {
            log.debug("Parsing courtyard from input file: {}", inputFilePath);
            Courtyard courtyard = courtyardParser.parse(inputFilePath);

            log.debug("Starting patrol simulation...");
            SimulationResult result = patrolSimulator.simulateWithAnalysis(courtyard);

            log.info("Simulation complete. Total unique spaces occupied by guards: {}", result.getUniqueSpacesOccupied());
            log.info("Total loop inducing obstacle placements: {}", result.getLoopInducingPlacements().size());
        } catch (Exception e) {
            log.error("An error occurred during the simulation: {}", e.getMessage(), e);
        }
    }
}