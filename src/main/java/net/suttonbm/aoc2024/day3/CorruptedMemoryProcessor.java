package net.suttonbm.aoc2024.day3;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CorruptedMemoryProcessor implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CorruptedMemoryProcessor.class);

    /**
     * Reads the input file located at classpath:/3/input.txt and returns the content as a list of strings.
     *
     * @return List of strings representing the input data
     */
    public String readInputFile() {
        StringBuilder inputData = new StringBuilder();

        try {
            ClassPathResource resource = new ClassPathResource("3/input.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                inputData.append(line);
                inputData.append("|");
            }
        } catch (IOException e) {
            logger.error("Error reading input file: {}", e.getMessage());
        }

        return inputData.toString();
    }

    public static List<String> processString(String input) {
        // Split the string using the regex pattern matching "don't()" and "do()"
        String[] parts = input.split("(?<=don't\\(\\)).*?(?=do\\(\\))");

        // Create a list from the split parts
        List<String> resultList = new ArrayList<>();
        for (String part : parts) {
            resultList.add(part);
        }

        // Check if the last element contains "don't()" and remove it if true
        if (!resultList.isEmpty() && resultList.get(resultList.size() - 1).contains("don't()")) {
            resultList.remove(resultList.size() - 1);
        }

        return resultList;
    }

    /**
     * Scans the corrupted memory, identifies valid "mul" instructions, processes the multiplications, and returns the sum.
     *
     * @param memory The corrupted memory as a string
     * @return The sum of the results of all valid "mul" instructions
     */
    public int scanCorruptedMemory(String memory) {
        int sum = 0;
        List<CorruptedInstruction> instructions = parseInstructions(memory);

        for (CorruptedInstruction instruction : instructions) {
            if (instruction.isValid()) {
                sum += instruction.getResults().stream()
                        .mapToInt(Integer::intValue)
                        .sum();
            } else {
                logger.debug("Invalid instruction: {}", instruction.getOriginalInstruction());
            }
        }

        return sum;
    }

    /**
     * Parses the corrupted memory string and returns a list of CorruptedInstruction objects.
     *
     * @param memory The corrupted memory as a string
     * @return List of CorruptedInstruction objects
     */
    private List<CorruptedInstruction> parseInstructions(String memory) {
        List<CorruptedInstruction> instructions = new ArrayList<>();
        String[] parts = memory.split(" ");

        for (String part : parts) {
            CorruptedInstruction instruction = new CorruptedInstruction(part);
            instructions.add(instruction);
        }

        return instructions;
    }

    @Override
    public void run(String... args) throws Exception {
        String input = readInputFile();
        int result = scanCorruptedMemory(input);

        log.info("Sum of valid mul(): {}", result);

        List<String> processedInput  = processString(input);
        int result2 = processedInput.stream().map(this::scanCorruptedMemory).reduce(0, Integer::sum);

        log.info("Sum of valid mul() with conditions: {}", result2);
    }
}