package net.suttonbm.aoc2024.day12.service;

import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day12.model.Garden;
import net.suttonbm.aoc2024.utils.ResourceReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GardenReader {
    public char[][] readGarden(String inputFile) {
        List<String> fileLines = ResourceReader.get(inputFile);
        char[][] data = new char[fileLines.get(0).length()][fileLines.size()];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = fileLines.get(i).charAt(j);
            }
        }
        return data;
    }
}
