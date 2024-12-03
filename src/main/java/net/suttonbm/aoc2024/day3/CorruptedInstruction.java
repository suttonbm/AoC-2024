package net.suttonbm.aoc2024.day3;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class CorruptedInstruction {
    private static final Pattern VALID_INSTRUCTION_PATTERN = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");

    private final String originalInstruction;
    private final List<Integer> results;

    public CorruptedInstruction(String instruction) {
        this.originalInstruction = instruction;
        this.results = parseInstruction();
    }

    private List<Integer> parseInstruction() {
        List<Integer> results = new ArrayList<>();
        Matcher matcher = VALID_INSTRUCTION_PATTERN.matcher(originalInstruction);

        while (matcher.find()) {
            try {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                results.add(x * y);
            } catch (NumberFormatException e) {
                // Ignore invalid numbers and continue to the next match
            }
        }

        return results;
    }

    public boolean isValid() {
        return !results.isEmpty();
    }

    public List<Integer> getResults() {
        return results;
    }

    public String getOriginalInstruction() {
        return originalInstruction;
    }
}