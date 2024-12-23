package net.suttonbm.aoc2024.day23;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day23.model.Edge;
import net.suttonbm.aoc2024.day23.model.LanNetwork;
import net.suttonbm.aoc2024.day23.model.Node;
import net.suttonbm.aoc2024.day23.service.NetworkMapper;
import net.suttonbm.aoc2024.day23.service.NetworkReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class LanPartyRunner implements CommandLineRunner {

    private final NetworkReader networkReader;
    private final NetworkMapper mapper;

    @Override
    public void run(String... args) throws Exception {
        LanNetwork network = networkReader.load("23/input.txt");
        Set<Set<String>> clusters = mapper.getClusters(network);
        int result = 0;
        for (Set<String> cluster : clusters) {
            for (String node : cluster) {
                if (node.startsWith("t")) {
                    result++;
                    break;
                }
            }
        }
        log.info("There are {} clusters with a 'T' participant", result);

        Set<Set<String>> bigClusters = mapper.getBigClusters(network);
        Set<String> maxCluster = new HashSet<>();
        int maxClusterSize = 0;
        for (Set<String> cluster : bigClusters) {
            if (cluster.size() > maxClusterSize) {
                maxCluster = cluster;
                maxClusterSize = cluster.size();
            }
        }
        String password = maxCluster.stream().sorted().collect(Collectors.joining(","));
        log.info("LAN Party Password: {}", password);
    }
}