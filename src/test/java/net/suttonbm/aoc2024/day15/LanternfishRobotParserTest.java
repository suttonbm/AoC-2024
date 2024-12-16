package net.suttonbm.aoc2024.day15;

import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import net.suttonbm.aoc2024.day15.service.LanternfishRobotParser;
import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;

public class LanternfishRobotParserTest {
    final LanternfishRobotParser parser = new LanternfishRobotParser();

    @Test
    public void testSimpleParse() {
        LanternfishWarehouse wh = parser.load("15/test.txt");

        System.out.println(wh);

        assertThat(wh).isNotNull();
        assertThat(wh.getWalls().length).isEqualTo(10);
        assertThat(wh.getWalls()[0].length).isEqualTo(10);
        assertThat(wh.getBoxes().length).isEqualTo(10);
        assertThat(wh.getBoxes()[0].length).isEqualTo(10);
        assertThat(wh.getRobot()).isEqualTo(new Point(4, 4));
        assertThat(wh.getRobotOps()).isNotEmpty();
        assertThat(wh.getRobotOps().length).isEqualTo(700);
        assertThat(wh.getRobotOps()[0]).isEqualTo('<');
        assertThat(wh.getRobotOps()[wh.getRobotOps().length-1]).isEqualTo('^');
    }
}
