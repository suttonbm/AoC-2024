package net.suttonbm.aoc2024.day25;

import net.suttonbm.aoc2024.day25.model.Key;
import net.suttonbm.aoc2024.day25.model.KeyData;
import net.suttonbm.aoc2024.day25.model.Keyhole;
import net.suttonbm.aoc2024.day25.service.KeyholeReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyholeTest {

    private final KeyholeReader reader = new KeyholeReader();

    @Test
    void testKeyholeFits() {
        List<String> keyInput = List.of(
                ".....",
                "#....",
                "#....",
                "#...#",
                "#.#.#",
                "#.###",
                "#####"
        );

        List<String> keyholeInput = List.of(
                "#####",
                ".####",
                ".####",
                ".###.",
                ".#.#.",
                ".#...",
                "....."
        );

        Key testKey = reader.parseKey(keyInput);
        Keyhole testKeyHole = reader.parseKeyHole(keyholeInput);
        assertThat(testKeyHole.fits(testKey)).isTrue();
    }

    @Test
    void testKeyholeDoesntFit() {
        List<String> keyInput = List.of(
                ".....",
                "#....",
                "#....",
                "#.#.#",
                "#.#.#",
                "#.###",
                "#####"
        );

        List<String> keyholeInput = List.of(
                "#####",
                ".####",
                ".####",
                ".###.",
                ".#.#.",
                ".#...",
                "....."
        );

        Key testKey = reader.parseKey(keyInput);
        Keyhole testKeyHole = reader.parseKeyHole(keyholeInput);
        assertThat(testKeyHole.fits(testKey)).isFalse();
    }

    @Test
    void testCount() {
        KeyData test = reader.read("25/test.txt");
        long result = test.possibleMatches();
        assertThat(result).isEqualTo(3L);
    }
}