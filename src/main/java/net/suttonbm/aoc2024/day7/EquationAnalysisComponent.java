package net.suttonbm.aoc2024.day7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day7.model.Equation;
import net.suttonbm.aoc2024.day7.service.EquationAnalyzer;
import net.suttonbm.aoc2024.day7.service.EquationParser;
import net.suttonbm.aoc2024.day7.service.EquationSummarizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class EquationAnalysisComponent implements CommandLineRunner {

    private final EquationParser equationParser;
    private final EquationAnalyzer equationAnalyzer;
    private final EquationSummarizer equationSummarizer;

    @Override
    public void run(String... args) {
        List<Equation> equations = equationParser.parseEquations();
        List<Equation> validEquations = equations.stream()
                .filter(equationAnalyzer::isEquationValid)
                .toList();
        equationSummarizer.summarizeValidEquations(validEquations);
    }
}