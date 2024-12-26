package net.suttonbm.aoc2024.day25.service;

import net.suttonbm.aoc2024.day25.model.Key;
import net.suttonbm.aoc2024.day25.model.KeyData;
import net.suttonbm.aoc2024.day25.model.Keyhole;
import net.suttonbm.aoc2024.utils.ResourceReaderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyholeReader {
    public KeyData read(String inputFile) {
        List<String> data = ResourceReaderService.get(inputFile);
        List<List<String>> blocks = new ArrayList<>();
        List<String> block = new ArrayList<>();

        for (String line : data) {
            if (line.isEmpty()) {
                blocks.add(block);
                block = new ArrayList<>();
                continue;
            }

            block.add(line);
        }
        blocks.add(block);

        List<Key> keys = new ArrayList<>();
        List<Keyhole> keyHoles = new ArrayList<>();
        for (List<String> blockLine : blocks) {
            if (blockLine.get(0).equals("#####")) {
                keyHoles.add(parseKeyHole(blockLine));
            } else {
                keys.add(parseKey(blockLine));
            }
        }

        return new KeyData(keys, keyHoles);
    }

    public Keyhole parseKeyHole(List<String> block) {
        return new Keyhole(parseBlock(block));
    }

    public Key parseKey(List<String> block) {
        return new Key(parseBlock(block));
    }

    private boolean[][] parseBlock(List<String> block) {
        boolean[][] result = new boolean[block.size()][block.get(0).length()];
        for (int i=0; i<result.length; i++) {
            for (int j=0; j<result[i].length; j++) {
                char c = block.get(i).charAt(j);
                if (c == '#') {
                    result[i][j] = true;
                }
            }
        }
        return result;
    }
}