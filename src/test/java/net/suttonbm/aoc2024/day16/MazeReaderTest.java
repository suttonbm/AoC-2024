package net.suttonbm.aoc2024.day16;

import net.suttonbm.aoc2024.day16.model.Maze;
import net.suttonbm.aoc2024.day16.service.MazeReader;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

public class MazeReaderTest {

    final MazeReader reader = new MazeReader();

    @Test
    void test() throws Exception {
        Maze maze = reader.load("16/test.txt");

        assertThat(maze).isNotNull();

        System.out.println(maze);

        assertThat(maze.getWalls().length).isEqualTo(17);
        assertThat(maze.getWalls()[0].length).isEqualTo(17);
        assertThat(maze.getReindeer().location()).isEqualTo(new Point(1, 15));
        assertThat(maze.getFinish()).isEqualTo(new Point(15, 1));
    }
}
