package net.suttonbm.aoc2024.day19;

import net.suttonbm.aoc2024.day19.model.TowelPuzzle;
import net.suttonbm.aoc2024.day19.service.TowelReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TowelPuzzleTest {

    private final TowelReader reader = new TowelReader();

    @Test
    void testExample() {
        TowelPuzzle puzzle = reader.load("19/test.txt");
        long result = puzzle.solve();
        assertThat(result).isEqualTo(6);
    }
}
