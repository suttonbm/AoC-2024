package net.suttonbm.aoc2024.day13;

import net.suttonbm.aoc2024.day13.model.ClawMachine;
import net.suttonbm.aoc2024.day13.service.ClawMachineReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClawMachineReaderTest {

    private final ClawMachineReader reader = new ClawMachineReader();

    @Test
    public void testRead() throws Exception {
        List<ClawMachine> input = reader.parse("13/test.txt");

        assertThat(input.size()).isEqualTo(4);
        assertThat(input.get(0)).isInstanceOf(ClawMachine.class);

        ClawMachine machine = input.get(0);
        assertThat(machine.getA()).isNotNull();
        assertThat(machine.getB()).isNotNull();
        assertThat(machine.getPrize()).isNotNull();

        assertThat(machine.getA().getX()).isEqualTo(94);
        assertThat(machine.getA().getY()).isEqualTo(34);
        assertThat(machine.getPrize().x).isEqualTo(8400);
        assertThat(machine.getPrize().y).isEqualTo(5400);
    }
}
