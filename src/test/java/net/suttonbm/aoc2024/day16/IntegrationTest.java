package net.suttonbm.aoc2024.day16;

import net.suttonbm.aoc2024.day16.model.Maze;
import net.suttonbm.aoc2024.day16.service.MazeReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    private final MazeReader reader = new MazeReader();

    @Test
    void testOne() throws Exception {
        Maze maze = reader.load("16/test.txt");
        maze.run();
        long score = maze.getCost();
        int occupied = maze.visitedInBestPaths();

        assertThat(score).isEqualTo(11048L);
        assertThat(occupied).isEqualTo(64);
    }

    @Test
    void testTwo() throws Exception {
        Maze maze = reader.load("16/test2.txt");
        maze.run();
        long score = maze.getCost();
        int occupied = maze.visitedInBestPaths();

        assertThat(score).isEqualTo(7036L);
        assertThat(occupied).isEqualTo(45);
    }
}
