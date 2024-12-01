package net.suttonbm.aoc2024.d1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class D1A implements CommandLineRunner {

    final ResourceLoader resourceLoader;

    static final String inputPath = "1/A/input.txt";

    @Override
    public void run(String... args) throws Exception {
        File input = resourceLoader.getResource(inputPath).getFile();
        List<List<Integer>> data = getDataFrom(input);
        int distance = runDistanceAlgo(data);
        log.info("Distance: {}", distance);
        long similarity = runSimilarityAlgo(data);
        log.info("Similarity: {}", similarity);
    }

    protected int runDistanceAlgo(List<List<Integer>> data) {
        List<Integer> col1 = data.get(0).stream().sorted().toList();
        List<Integer> col2 = data.get(1).stream().sorted().toList();

        int result = 0;
        for (int i = 0; i < col1.size(); i++) {
            result += Math.abs(col1.get(i) - col2.get(i));
        }

        return result;
    }

    protected long runSimilarityAlgo(List<List<Integer>> data) {
        List<Integer> col1 = data.get(0);
        List<Integer> col2 = data.get(1);

        long result = col1.stream().map(x -> {
            long times = col2.stream().filter(x::equals).count();
            log.info("Similarity: {} appears {} times", x, times);
            return x * times;
        }).mapToLong(i -> i).sum();

        return result;
    }

    protected List<List<Integer>> getDataFrom(File file) throws FileNotFoundException {
        Reader reader = new InputStreamReader(new FileInputStream(file));
        BufferedReader fileReader = new BufferedReader(reader);
        List<Integer> col1 = new ArrayList<>();
        List<Integer> col2 = new ArrayList<>();

        fileReader.lines().forEach(line -> {
            String[] row = line.split("\\s+");
            col1.add(Integer.parseInt(row[0]));
            col2.add(Integer.parseInt(row[1]));
        });

        return List.of(col1, col2);
    }
}