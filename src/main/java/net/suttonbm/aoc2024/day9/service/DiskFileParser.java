package net.suttonbm.aoc2024.day9.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day9.model.File;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DiskFileParser {

    public List<File> parseInputFile(String filePath) {
        char[] line;
        try {
            ClassPathResource resource = new ClassPathResource(filePath);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                line = reader.readLine().strip().toCharArray();
            }
        } catch (IOException e) {
            log.error("Error reading input file: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        List<File> result = new ArrayList<>();

        int fileId = 0;
        for (int i=0; i<line.length; i++) {
            int size = Integer.parseInt(String.valueOf(line[i]));
            if (i % 2 == 0) {
                for (int b=0; b<size; b++) {
                    result.add(new File(fileId, size));
                }
                fileId++;
            } else {
                for (int b=0; b<size; b++) {
                    result.add(new File(null, size));
                }
            }
        }

        return result;
    }
}