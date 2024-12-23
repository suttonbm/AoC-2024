package net.suttonbm.aoc2024.day23.service;

import net.suttonbm.aoc2024.day23.model.Edge;
import net.suttonbm.aoc2024.day23.model.LanNetwork;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NetworkReader {
    public LanNetwork load(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);

        LanNetwork lanNetwork = new LanNetwork();
        for (String line : data) {
            String[] parts = line.split("-");
            lanNetwork.addEdge(new Edge(parts[0], parts[1]));
        }

        return lanNetwork;
    }
}