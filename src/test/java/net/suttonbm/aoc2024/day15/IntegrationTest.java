package net.suttonbm.aoc2024.day15;

import net.suttonbm.aoc2024.day15.model.BiggerLanternfishWarehouse;
import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import net.suttonbm.aoc2024.day15.service.LanternfishRobotParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

    final LanternfishRobotParser parser = new LanternfishRobotParser();

    @Test
    public void test() {
        LanternfishWarehouse wh = parser.load("15/test.txt");
        System.out.println(wh);
        wh.run();
        System.out.println(wh);

        long result = wh.getCoordinates().stream().mapToLong(Long::valueOf).sum();
        assertThat(result).isEqualTo(10092L);
    }

    @Test
    public void testBigger() {
        BiggerLanternfishWarehouse wh = parser.loadBigger("15/test.txt");
        System.out.println(wh);
        wh.run();
        System.out.println(wh);

        long result = wh.getCoordinates().stream().mapToLong(Long::valueOf).sum();
        assertThat(result/2).isEqualTo(9021L);
    }
}
