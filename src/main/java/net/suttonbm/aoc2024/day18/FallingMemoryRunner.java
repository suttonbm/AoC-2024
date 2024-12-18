package net.suttonbm.aoc2024.day18;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day18.model.MemoryPathSearch;
import net.suttonbm.aoc2024.day18.service.MemoryReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.awt.Point;

@Component
@Slf4j
@RequiredArgsConstructor
public class FallingMemoryRunner implements CommandLineRunner {

    private final MemoryReader reader;

    @Override
    public void run(String... args) throws Exception {
        List<Point> data = reader.load("18/input.txt");
        MemoryPathSearch mps = new MemoryPathSearch(data, 71, new Point(0,0), new Point(70,70));
        int result = mps.getBestPathAt(1024);
        log.info("Shortest path at 1024 is {} units.", result);
        Point badPoint = mps.getBlockingPoint(1024);
        log.info("Bad point is {}.", badPoint);
    }
}
