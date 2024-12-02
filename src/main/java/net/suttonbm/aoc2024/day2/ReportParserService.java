package net.suttonbm.aoc2024.day2;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportParserService {

    private final ResourceLoader resourceLoader;

    public ReportParserService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<List<Integer>> parseReports(String resourcePath) throws IOException {
        List<List<Integer>> reports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resourceLoader.getResource(resourcePath).getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> reportLevels = parseLineToNumbers(line);
                reports.add(reportLevels);
            }
        }
        return reports;
    }

    private List<Integer> parseLineToNumbers(String line) {
        List<Integer> levels = new ArrayList<>();
        String[] tokens = line.trim().split("\\s+");
        for (String token : tokens) {
            levels.add(Integer.parseInt(token));
        }
        return levels;
    }
}