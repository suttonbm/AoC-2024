package net.suttonbm.aoc2024.day10;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day10.model.TopoMap;
import net.suttonbm.aoc2024.day10.model.Trail;
import net.suttonbm.aoc2024.day10.service.TrailFinder;
import net.suttonbm.aoc2024.day10.service.TopoMapParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrailRunner implements CommandLineRunner {

    private final TopoMapParser inputFileParser;
    private final TrailFinder trailFinder;

    @Override
    public void run(String... args) throws Exception {
        String inputFilePath = "10/input.txt";
        TopoMap input = inputFileParser.parseInputFile(inputFilePath);
        List<Trail> trails = trailFinder.search(input);
        int totalScore = trails.stream()
                .map(trail -> trail.getScore())
                .reduce(0, Integer::sum);
        int totalRating = trails.stream()
                .map(trail -> trail.getRating())
                .reduce(0, Integer::sum);
        log.info("Total trail score: {}", totalScore);
        log.info("Total trail rating: {}", totalRating);
    }
}
