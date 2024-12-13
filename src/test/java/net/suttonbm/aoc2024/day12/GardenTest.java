package net.suttonbm.aoc2024.day12;

import net.suttonbm.aoc2024.day12.model.Garden;
import net.suttonbm.aoc2024.day12.service.GardenReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GardenTest {

    private final GardenReader reader = new GardenReader();

    @Test
    public void simpleTest() {
        char[][] data = reader.readGarden("12/simple.txt");
        Garden garden = new Garden(data);
        System.out.println(garden);

        assertThat(garden.getPlots().size()).isEqualTo(16);
        assertThat(garden.getRegions().size()).isEqualTo(5);
        assertThat(garden.getFenceCost()).isEqualTo(140);
    }

    @Test
    public void exampleTest() {
        char[][] data = reader.readGarden("12/example.txt");
        Garden garden = new Garden(data);
        System.out.println(garden);

        assertThat(garden.getRegions().size()).isEqualTo(11);
        assertThat(garden.getFenceCost()).isEqualTo(1930);
    }

    @Test
    public void simpleBulkTest() {
        char[][] data = reader.readGarden("12/simple.txt");
        Garden garden = new Garden(data);
        System.out.println(garden);

        assertThat(garden.getDiscountedCost()).isEqualTo(80);
    }
}
