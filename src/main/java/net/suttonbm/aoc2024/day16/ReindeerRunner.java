package net.suttonbm.aoc2024.day16;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day16.model.Maze;
import net.suttonbm.aoc2024.day16.service.MazeReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
public class ReindeerRunner implements CommandLineRunner {

    private final MazeReader reader;

    public void run(String... args) throws Exception {
        Maze maze = reader.load("16/input.txt");
        maze.run();
        long score = maze.getCost();
        log.info("Minimum score to reach the finish is {}", score);
        int occupied = maze.visitedInBestPaths();
        log.info("{} squares were occupied", occupied);
    }
}
