package net.suttonbm.aoc2024.day23.service;

import net.suttonbm.aoc2024.day23.model.Edge;
import net.suttonbm.aoc2024.day23.model.LanNetwork;
import net.suttonbm.aoc2024.day23.model.Node;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class NetworkMapper {
    public Set<Set<String>> getClusters(LanNetwork network) {
        Set<Set<String>> result = new HashSet<>();
        for (Edge e1 : network.getEdges()) {
            Node n1 = network.getNode(e1.a());
            Node n2 = network.getNode(e1.b());

            for (Edge e2 : n1.getConnections()) {
                if (e2.equals(e1)) {
                    continue;
                }
                if (n2.contains(e2.a()) && n2.contains(e2.b())) {
                    Set<String> cluster = new HashSet<>();
                    cluster.add(e1.a());
                    cluster.add(e2.a());
                    cluster.add(e1.b());
                    cluster.add(e2.b());
                    result.add(cluster);
                }
            }
        }

        return result;
    }

    public Set<Set<String>> getBigClusters(LanNetwork network) {
        Set<Set<String>> result = new HashSet<>();
        for (Edge e : network.getEdges()) {
            Node n1 = network.getNode(e.a());
            Node n2 = network.getNode(e.b());

            boolean n1Clustered = false;
            boolean n2Clustered = false;

            for (Set<String> cluster : result) {
                if (n1.containsAll(cluster)) {
                    cluster.add(n1.getName());
                    n1Clustered = true;
                }
                if (n2.containsAll(cluster)) {
                    cluster.add(n2.getName());
                    n2Clustered = true;
                }
            }

            if (!n1Clustered && !n2Clustered) {
                Set<String> cluster = new HashSet<>();
                cluster.add(n1.getName());
                cluster.add(n2.getName());
                result.add(cluster);
            }
        }

        return result;
    }
}