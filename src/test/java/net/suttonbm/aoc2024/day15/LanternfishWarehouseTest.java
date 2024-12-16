package net.suttonbm.aoc2024.day15;

import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.Point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanternfishWarehouseTest {
    @Test
    void testSimpleOp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        Point robot = new Point(1, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(0, 1));

        cut.doOp('>');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));

        cut.doOp('^');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 0));

        cut.doOp('v');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
    }

    @Test
    void testPushBlockRight() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(0, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('>');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
        assertThat(cut.getBoxes()[1][1]).isFalse();
        assertThat(cut.getBoxes()[2][1]).isTrue();
    }

    @Test
    void testPushBlockLeft() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(2, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
        assertThat(cut.getBoxes()[1][1]).isFalse();
        assertThat(cut.getBoxes()[0][1]).isTrue();
    }

    @Test
    void testPushBlockUp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(1, 2);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
        assertThat(cut.getBoxes()[1][1]).isFalse();
        assertThat(cut.getBoxes()[1][0]).isTrue();
    }

    @Test
    void testPushBlockDown() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(1, 0);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('v');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
        assertThat(cut.getBoxes()[1][1]).isFalse();
        assertThat(cut.getBoxes()[1][2]).isTrue();
    }

    @Test
    void testPushWallLeft() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(2, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
    }

    @Test
    void testPushWallRight() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(0, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('>');
        assertThat(cut.getRobot()).isEqualTo(new Point(0, 1));
    }

    @Test
    void testPushWallUp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(1, 2);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 2));
    }

    @Test
    void testPushWallDown() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(1, 0);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('v');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 0));
    }

    @Test
    void testPushBoxIntoWall() {
        boolean[][] walls = {
                {true, true, true, true},
                {true, false, false, true},
                {true, false, false, true},
                {true, true, true, true},
        };
        boolean[][] boxes = new boolean[4][4];
        boxes[1][1] = true;
        Point robot = new Point(2, 1);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
        assertThat(cut.getBoxes()[1][1]).isTrue();
        assertThat(cut.getBoxes()[0][1]).isFalse();
    }

    @Test
    void testPushMultipleBoxes() {
        boolean[][] walls = {
                {true, true, true, true, true, true},
                {true, false, false, false, false, true},
                {true, false, false, false, false, true},
                {true, true, true, true, true, true},
        };
        boolean[][] boxes = new boolean[4][6];
        boxes[1][2] = true;
        boxes[1][3] = true;
        Point robot = new Point(1, 4);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 3));
        assertThat(cut.getBoxes()[1][1]).isTrue();
        assertThat(cut.getBoxes()[1][2]).isTrue();
        assertThat(cut.getBoxes()[1][3]).isFalse();
    }

    @Test
    void testPushMultipleBoxesIntoWall() {
        boolean[][] walls = {
                {true, true, true, true, true, true},
                {true, false, false, false, false, true},
                {true, false, false, false, false, true},
                {true, true, true, true, true, true},
        };
        boolean[][] boxes = new boolean[4][6];
        boxes[1][1] = true;
        boxes[1][2] = true;
        Point robot = new Point(1, 3);

        LanternfishWarehouse cut = new LanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 3));
        assertThat(cut.getBoxes()[1][1]).isTrue();
        assertThat(cut.getBoxes()[1][2]).isTrue();
    }
}
