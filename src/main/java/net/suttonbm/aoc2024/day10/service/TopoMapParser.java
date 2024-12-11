package net.suttonbm.aoc2024.day10.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day10.model.TopoMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TopoMapParser {
    public TopoMap parseInputFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            log.error("Error reading input file: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        int rows = lines.size();
        int cols = lines.get(0).length();

        int[][] data = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Integer.parseInt(
                        String.valueOf(lines.get(i).charAt(j))
                );
            }
        }

        return new TopoMap(data);
    }
}
