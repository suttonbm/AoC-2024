package net.suttonbm.aoc2024.day7;

import net.suttonbm.aoc2024.day7.model.Equation;
import net.suttonbm.aoc2024.day7.service.EquationAnalyzer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquationAnalyzerTests {
    private final EquationAnalyzer analyzer = new EquationAnalyzer();

    @ParameterizedTest
    @MethodSource("validEquationProvider")
    void testValidEquations(Equation equation) {
        assertTrue(analyzer.isEquationValid(equation));
    }

    @ParameterizedTest
    @MethodSource("invalidEquationProvider")
    void testInvalidEquations(Equation equation) {
        assertFalse(analyzer.isEquationValid(equation));
    }

    static Stream<Arguments> validEquationProvider() {
        return Stream.of(
                Arguments.of(new Equation(190L, List.of(10L, 19L))),
                Arguments.of(new Equation(3267L, List.of(81L, 40L, 27L))),
                Arguments.of(new Equation(292L, List.of(11L, 6L, 16L, 20L)))
        );
    }

    static Stream<Arguments> invalidEquationProvider() {
        return Stream.of(
                Arguments.of(new Equation(83L, List.of(17L, 5L))),
                Arguments.of(new Equation(156L, List.of(15L, 6L))),
                Arguments.of(new Equation(7290L, List.of(6L, 8L, 6L, 15L))),
                Arguments.of(new Equation(161011L, List.of(16L, 10L, 13L))),
                Arguments.of(new Equation(192L, List.of(17L, 8L, 14L))),
                Arguments.of(new Equation(21037L, List.of(9L, 7L, 18L, 13L)))
        );
    }
}