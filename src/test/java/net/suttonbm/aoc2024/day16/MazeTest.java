package net.suttonbm.aoc2024.day16;

import net.suttonbm.aoc2024.day16.service.MazeReader;
import net.suttonbm.aoc2024.day16.model.Maze;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class MazeTest {

    final MazeReader reader = new MazeReader();

    @Test
    void simpleTest() throws Exception {
        Maze maze = reader.parse(List.of(
                "####",
                "#.E#",
                "#S.#",
                "####"
        ));

        maze.run();

        assertThat(maze.getCost()).isEqualTo(1002L);
    }
}
