package net.suttonbm.aoc2024.day12;

import net.suttonbm.aoc2024.day12.service.GardenReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GardenReaderTest {

    private final GardenReader gardenReader = new GardenReader();

    @Test
    public void testReadSinglePlot() throws Exception {
        char[][] data = gardenReader.readGarden("12/simple.txt");

        assertThat(data.length).isEqualTo(4);
        assertThat(data[0].length).isEqualTo(4);
    }
}
