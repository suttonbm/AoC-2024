package net.suttonbm.aoc2024.day13;

import net.suttonbm.aoc2024.day13.model.ClawMachine;
import net.suttonbm.aoc2024.day13.model.Operation;
import net.suttonbm.aoc2024.day13.model.Prize;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ClawMachineTest {
    @Test
    public void testSuccess() {
        ClawMachine test = new ClawMachine(0);
        test.setA(new Operation(94, 34));
        test.setB(new Operation(22, 67));
        test.setPrize(new Prize(8400, 5400));

        float cost = test.prizeCost();

        assertThat(cost).isEqualTo(280);
    }

    @Test
    public void testSuccess2() {
        ClawMachine test = new ClawMachine(0);
        test.setA(new Operation(17, 86));
        test.setB(new Operation(84, 37));
        test.setPrize(new Prize(7870, 6450));

        float cost = test.prizeCost();

        assertThat(cost).isEqualTo(200);
    }

    @Test
    public void testFail() {
        ClawMachine test = new ClawMachine(0);
        test.setA(new Operation(26, 66));
        test.setB(new Operation(67, 21));
        test.setPrize(new Prize(12748, 12176));

        float cost = test.prizeCost();

        assertThat(cost).isEqualTo(0);
    }

    @Test
    public void testBigSuccess() {
        ClawMachine test = new ClawMachine(0);
        test.setA(new Operation(26, 66));
        test.setB(new Operation(67, 21));
        test.setPrize(new Prize(12748L, 12176L));

        long cost = test.prizeCost();

        assertThat(cost).isNotEqualTo(0);
    }
}
