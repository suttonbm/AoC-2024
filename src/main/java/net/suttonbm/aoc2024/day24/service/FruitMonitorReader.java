package net.suttonbm.aoc2024.day24.service;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day24.model.FruitMonitor;
import net.suttonbm.aoc2024.day24.model.Gate;
import net.suttonbm.aoc2024.day24.model.GateType;
import net.suttonbm.aoc2024.day24.model.Wire;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class FruitMonitorReader {
    public FruitMonitor load(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);
        Map<String, Wire> initWires = new HashMap<>();
        Set<Gate> initGates = new HashSet<>();

        boolean readingGates = true;
        for (int i = data.size()-1; i >= 0; i--) {
            String line = data.get(i);

            if (StringUtils.isBlank(line)) {
                readingGates = false;
                continue;
            }

            if (readingGates) {
                String[] tokens = line.split("\\s");
                Wire inputA = initWires.getOrDefault(tokens[0], new Wire(tokens[0]));
                Wire inputB = initWires.getOrDefault(tokens[2], new Wire(tokens[2]));
                Wire output = initWires.getOrDefault(tokens[4], new Wire(tokens[4]));
                GateType gateType = GateType.valueOf(tokens[1]);

                initGates.add(new Gate(inputA, inputB, output, gateType));
                initWires.put(tokens[0], inputA);
                initWires.put(tokens[2], inputB);
                initWires.put(tokens[4], output);
            } else {
                String[] tokens = line.split(":\\s");
                Wire wire = initWires.get(tokens[0]);
                wire.fire(tokens[1].equals("1") ? true : false);
            }
        }

        return new FruitMonitor(initWires, initGates);
    }
}