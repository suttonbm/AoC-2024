package net.suttonbm.aoc2024.day7.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.suttonbm.aoc2024.day7.model.Equation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EquationAnalyzer {

    public boolean isEquationValid(Equation equation) {
        return tryAllOperatorPermutations(equation, 0, equation.getValues().get(0));
    }

    private boolean tryAllOperatorPermutations(Equation equation, int index, long currentResult) {
        if (index == equation.getValues().size() - 1) {
            return currentResult == equation.getTotal();
        }

        long nextValueAdd = currentResult + equation.getValues().get(index + 1);

        if (tryAllOperatorPermutations(equation, index + 1, nextValueAdd)) {
            return true;
        }

        long nextValueMult = currentResult * equation.getValues().get(index + 1);

        if (tryAllOperatorPermutations(equation, index + 1, nextValueMult)) {
            return true;
        }

        String concatString = Long.toString(currentResult) + equation.getValues().get(index + 1).toString();
        long nextValueConcat = Long.parseLong(concatString);

        if (tryAllOperatorPermutations(equation, index + 1 , nextValueConcat)) {
            return true;
        }

        return false;
    }
}