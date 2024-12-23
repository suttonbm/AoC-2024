package net.suttonbm.aoc2024.day23;

import net.suttonbm.aoc2024.day23.model.LanNetwork;
import net.suttonbm.aoc2024.day23.service.NetworkReader;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ReaderTest {

    private final NetworkReader reader = new NetworkReader();

    @Test
    public void test() {
        LanNetwork network = reader.load("23/test.txt");
        assertThat(network.getEdges().size()).isEqualTo(32);
        assertThat(network.getNodes().size()).isEqualTo(16);
    }
}