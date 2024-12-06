package net.suttonbm.aoc2024.day6.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day6.model.Courtyard;
import net.suttonbm.aoc2024.day6.model.Guard;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourtyardParserService {

    /**
     * Parses the input file to create a Courtyard instance.
     * @param resourcePath Path to the input file in the classpath
     * @return Courtyard object containing grid and guards
     * @throws IOException If the file cannot be read
     */
    public Courtyard parse(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Input file is empty.");
            }

            int rows = lines.size();
            int cols = lines.get(0).length();
            char[][] grid = new char[rows][cols];
            List<Guard> guards = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                if (lines.get(i).length() != cols) {
                    throw new IllegalArgumentException("All rows must have the same length.");
                }

                for (int j = 0; j < cols; j++) {
                    char c = lines.get(i).charAt(j);
                    grid[i][j] = c;

                    if (isGuard(c)) {
                        guards.add(new Guard(i, j, c));
                    }
                }
            }

            log.debug("Successfully parsed courtyard with {} rows and {} columns.", rows, cols);
            log.debug("Found {} guards: {}", guards.size(), guards);

            return new Courtyard(grid, guards);
        }
    }

    private boolean isGuard(char c) {
        return c == '^' || c == 'v' || c == '<' || c == '>';
    }
}