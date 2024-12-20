package net.suttonbm.aoc2024.day20;

import net.suttonbm.aoc2024.day20.model.RaceMap;
import net.suttonbm.aoc2024.day20.service.MapReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RaceMapTests {

    private final MapReader reader = new MapReader();

    @Test
    void testInit() {
        RaceMap raceMap = reader.load("20/test.txt");
        assertThat(raceMap.getPath().size()).isEqualTo(85);
    }

    @Test
    void testCheats() {
        RaceMap raceMap = reader.load("20/test.txt");
        List<Integer> cheats = raceMap.getCheats();
        System.out.println(cheats);
    }

    @Test
    void testBetterCheats() {
        RaceMap raceMap = reader.load("20/test.txt");
        List<Integer> cheats = raceMap.getBetterCheats();
        System.out.println(cheats);
    }
}
