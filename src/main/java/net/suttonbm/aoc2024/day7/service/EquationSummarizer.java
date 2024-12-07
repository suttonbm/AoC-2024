package net.suttonbm.aoc2024.day7.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day7.model.Equation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquationSummarizer {

    private final EquationAnalyzer equationAnalyzer;

    public void summarizeValidEquations(List<Equation> equations) {
        long validTotal = 0;
        StringBuilder validEquationBuilder = new StringBuilder();

        for (Equation equation : equations) {
            if (equationAnalyzer.isEquationValid(equation)) {
                validTotal += equation.getTotal();
                validEquationBuilder.append(equation.getTotal())
                        .append(": ")
                        .append(String.join(" ", equation.getValues().stream()
                                .map(String::valueOf)
                                .toList()))
                        .append("\n");
            }
        }

        log.debug("Valid Equations:\n{}", validEquationBuilder);
        log.info("Sum of valid results: {}", validTotal);
    }
}