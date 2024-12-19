package net.suttonbm.aoc2024.day15;

import net.suttonbm.aoc2024.day15.model.BiggerLanternfishWarehouse;
import net.suttonbm.aoc2024.day15.model.LanternfishWarehouse;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BiggerLanternfishWarehouseTest {
    @Test
    void testConstruction() {
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

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
    }

    @Test
    void testSimpleOp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        Point robot = new Point(1, 1);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));

        cut.doOp('>');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));

        cut.doOp('^');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 0));

        cut.doOp('v');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
    }

    @Test
    void testPushBlockRight() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(0, 1);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('>');
        cut.doOp('>');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
    }

    @Test
    void testPushBlockLeft() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(2, 1);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(3, 1));
    }

    @Test
    void testPushBlockUp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(1, 2);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
    }

    @Test
    void testPushBlockDown() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        boxes[1][1] = true;
        Point robot = new Point(1, 0);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('v');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 1));
    }

    @Test
    void testPushWallLeft() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(2, 1);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(4, 1));
    }

    @Test
    void testPushWallRight() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(0, 1);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('>');
        cut.doOp('>');
        assertThat(cut.getRobot()).isEqualTo(new Point(1, 1));
    }

    @Test
    void testPushWallUp() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(1, 2);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 2));
    }

    @Test
    void testPushWallDown() {
        boolean[][] walls = new boolean[3][3];
        boolean[][] boxes = new boolean[3][3];
        walls[1][1] = true;
        Point robot = new Point(1, 0);

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('v');
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 0));
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

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('<');
        assertThat(cut.getRobot()).isEqualTo(new Point(4, 1));
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

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 3));
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

        BiggerLanternfishWarehouse cut = new BiggerLanternfishWarehouse(walls, boxes, robot, new char[1]);
        System.out.println(cut);
        cut.doOp('^');
        System.out.println(cut);
        assertThat(cut.getRobot()).isEqualTo(new Point(2, 3));
    }
}
