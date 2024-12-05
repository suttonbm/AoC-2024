package net.suttonbm.aoc2024.day4.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day4.exception.InvalidInputException;
import net.suttonbm.aoc2024.day4.exception.ResourceNotFoundException;
import net.suttonbm.aoc2024.day4.model.PuzzleMatrix;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceReader {
    public PuzzleMatrix readPuzzle(String resourcePath) {
        List<String> lines;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new ResourceNotFoundException("Resource not found: " + resourcePath);
            }
            lines = readLines(is);
        } catch (IOException e) {
            throw new ResourceNotFoundException("Error reading resource: " + resourcePath);
        }

        if (!validateInput(lines)) {
            throw new InvalidInputException("Input is not a rectangular puzzle");
        }

        return new PuzzleMatrix(convertToMatrix(lines));
    }

    private boolean validateInput(List<String> lines) {
        int expectedLength = lines.get(0).length();
        for (String line : lines) {
            if (line.length() != expectedLength) {
                return false;
            }
        }
        return true;
    }

    public char[][] convertToMatrix(List<String> lines) {
        char[][] matrix = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                matrix[i][j] = lines.get(i).charAt(j);
            }
        }

        log.info("Matrix has {} rows and {} columns", matrix.length, matrix[0].length);

        return matrix;
    }

    private List<String> readLines(InputStream is) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(is)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }
}