package net.suttonbm.aoc2024.day25;

import net.suttonbm.aoc2024.day25.model.Key;
import net.suttonbm.aoc2024.day25.model.KeyData;
import net.suttonbm.aoc2024.day25.model.Keyhole;
import net.suttonbm.aoc2024.day25.service.KeyholeReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyholeReaderTest {

    private final KeyholeReader reader = new KeyholeReader();

    @Test
    void testParseKey() {
        List<String> input = List.of(
                ".....",
                "#....",
                "#....",
                "#...#",
                "#.#.#",
                "#.###",
                "#####"
        );

        boolean[][] answer = {
                {false, false, false, false, false},
                {true, false, false, false, false},
                {true, false, false, false, false},
                {true, false, false, false, true},
                {true, false, true, false, true},
                {true, false, true, true, true},
                {true, true, true, true, true},
        };

        Key test = reader.parseKey(input);
        assertThat(test.heights()).isDeepEqualTo(answer);
    }

    @Test
    void testParseFile() {
        KeyData test = reader.read("25/test.txt");
        assertThat(test).isNotNull();
        assertThat(test.numKeyHoles()).isEqualTo(2);
        assertThat(test.numKeys()).isEqualTo(3);
    }
}