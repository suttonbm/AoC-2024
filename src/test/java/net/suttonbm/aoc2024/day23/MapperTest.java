package net.suttonbm.aoc2024.day23;

import net.suttonbm.aoc2024.day23.model.LanNetwork;
import net.suttonbm.aoc2024.day23.service.NetworkMapper;
import net.suttonbm.aoc2024.day23.service.NetworkReader;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class MapperTest {

    private final NetworkMapper mapper = new NetworkMapper();
    private final NetworkReader reader = new NetworkReader();

    @Test
    void test() {
        LanNetwork network = reader.load("23/test.txt");
        Set<Set<String>> result = mapper.getClusters(network);
        System.out.println(result);
    }

    @Test
    void test2() {
        LanNetwork network = reader.load("23/test.txt");
        Set<Set<String>> result = mapper.getBigClusters(network);
        System.out.println(result);
    }
}