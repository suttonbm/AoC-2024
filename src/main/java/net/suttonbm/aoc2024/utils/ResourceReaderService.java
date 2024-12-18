package net.suttonbm.aoc2024.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ResourceReaderService {
    public static List<String> get(String filePath) {
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
        return lines;
    }
}
