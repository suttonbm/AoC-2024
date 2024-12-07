package net.suttonbm.aoc2024.day7.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day7.model.Equation;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EquationParser {

    private static final String INPUT_FILE_PATH = "7/input.txt";

    public List<Equation> parseEquations() {
        List<Equation> equations = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(INPUT_FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Equation equation = parseEquation(line);
                equations.add(equation);
            }
        } catch (IOException e) {
            log.error("Error reading input file", e);
        }
        return equations;
    }

    private Equation parseEquation(String line) {
        String[] parts = line.split(":");
        long total = Long.parseLong(parts[0]);
        String[] values = parts[1].trim().split(" ");
        List<Long> valueList = new ArrayList<>();
        for (String value : values) {
            valueList.add(Long.parseLong(value));
        }
        return new Equation(total, valueList);
    }
}