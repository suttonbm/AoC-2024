package net.suttonbm.aoc2024.day5.service;

import net.suttonbm.aoc2024.day5.model.Constraint;
import net.suttonbm.aoc2024.day5.model.ProposedUpdate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileParserService {

    private static final String FILE_PATH = "5/input.txt"; // Adjust this path as needed

    /**
     * Reads the input file and parses constraints and proposed updates.
     * @return a list where the first element is the list of constraints, and the second is the list of proposed updates.
     */
    public List<Object> parseFile() throws IOException {
        Resource resource = new ClassPathResource(FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            List<String> constraintLines = new ArrayList<>();
            List<String> proposedUpdateLines = new ArrayList<>();
            boolean isConstraintsSection = true;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Blank line indicates the start of the proposed updates section
                    isConstraintsSection = false;
                } else {
                    if (isConstraintsSection) {
                        constraintLines.add(line);
                    } else {
                        proposedUpdateLines.add(line);
                    }
                }
            }

            List<Constraint> constraints = parseConstraints(constraintLines);
            List<ProposedUpdate> proposedUpdates = parseProposedUpdates(proposedUpdateLines);

            List<Object> result = new ArrayList<>();
            result.add(constraints);
            result.add(proposedUpdates);

            return result;
        }
    }

    /**
     * Parses the constraints section into a list of Constraint objects.
     * @param lines the list of constraint lines
     * @return a list of Constraint objects
     */
    private List<Constraint> parseConstraints(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    String[] parts = line.split("\\|");
                    return new Constraint(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
                })
                .collect(Collectors.toList());
    }

    /**
     * Parses the proposed updates section into a list of ProposedUpdate objects.
     * @param lines the list of proposed update lines
     * @return a list of ProposedUpdate objects
     */
    private List<ProposedUpdate> parseProposedUpdates(List<String> lines) {
        return lines.stream()
                .map(line -> {
                    List<Integer> numbers = List.of(line.split(",")).stream()
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    return new ProposedUpdate(numbers);
                })
                .collect(Collectors.toList());
    }
}