package net.suttonbm.aoc2024.day18;

import net.suttonbm.aoc2024.day18.service.MemoryReader;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryReaderTest {

    final MemoryReader reader = new MemoryReader();

    @Test
    void test() {
        List<Point> data = reader.load("18/test.txt");
        assertThat(data.size()).isEqualTo(25);
    }
}
